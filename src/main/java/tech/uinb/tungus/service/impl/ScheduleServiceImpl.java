/**
 * Copyright 2021 UINB Technologies Pte. Ltd.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.uinb.tungus.service.impl;

import io.emeraldpay.polkaj.api.PolkadotMethod;
import io.emeraldpay.polkaj.api.RpcCall;
import io.emeraldpay.polkaj.api.StandardCommands;
import io.emeraldpay.polkaj.apihttp.PolkadotHttpApi;
import org.bouncycastle.util.encoders.Hex;
import tech.uinb.tungus.codec.EventReader;
import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.EventWriter;
import tech.uinb.tungus.codec.EventsReader;
import tech.uinb.tungus.codec.GeneralExtrinsicReader;
import tech.uinb.tungus.codec.fusotao.FusotaoEventReaderRegistry;
import tech.uinb.tungus.codec.fusotao.FusotaoEventWriterRegistry;
import tech.uinb.tungus.codec.fusotao.FusotaoExtrinsicReaderRegistry;
import io.emeraldpay.polkaj.json.BlockJson;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scaletypes.MetadataReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.ByteData;
import io.emeraldpay.polkaj.types.Hash256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.uinb.tungus.service.BlockService;
import tech.uinb.tungus.service.ScheduleService;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    private final State endState = () -> {
    };
    private final int MAX_SUBMIT = 1 << 10;
    @Autowired
    private PolkadotHttpApi client;
    @Autowired
    private BlockService blockService;

    private StandardCommands commands;
    private ThreadPoolExecutor executor;
    private Semaphore semaphore;
    private PriorityBlockingQueue<BlockResult> blockQueue = new PriorityBlockingQueue<>(10000);

    private AtomicBoolean master = new AtomicBoolean(true);
    private AtomicLong finalizedBlockNumber = new AtomicLong(-1);
    private AtomicLong nextReadNumber = new AtomicLong(Long.MIN_VALUE);
    private AtomicLong nextSaveNumber = new AtomicLong(Long.MIN_VALUE);
    private AtomicLong theadIdx = new AtomicLong(0);
    private AtomicReference<GeneralExtrinsicReader> extrinsicReader = new AtomicReference<>();
    private AtomicReference<EventsReader> eventsReader = new AtomicReference<>();
    private AtomicReference<EventReader> eventReader = new AtomicReference<>();
    private AtomicReference<EventWriter> eventWriter = new AtomicReference<>();

    public AtomicReference<EventWriter> getEventWriter() {
        return eventWriter;
    }

    private boolean addTasks() {
        int cnt = 0;
        while (nextReadNumber.get() <= finalizedBlockNumber.get()) {
            try {
                if (!semaphore.tryAcquire()) {
                    return true;
                }

                long id = nextReadNumber.get();
                executor.submit(new GetterRunnable(id));
                LOGGER.debug("add {} hash getter task", id);
                nextReadNumber.incrementAndGet();

                cnt++;
                if (cnt > MAX_SUBMIT) {
                    return true;
                }
            } catch (Throwable e) {
                semaphore.release();
            }
        }

        return false;
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 3000)
    @Override
    public void scheduleGetFinalizedHead() {

        if (nextReadNumber.get() == Long.MIN_VALUE) {
            var lastNumber = blockService.lastBlockNumber();
            if (lastNumber == null) {
                nextReadNumber.set(0L);
            } else {
                nextReadNumber.set(lastNumber + 1);
            }
            nextSaveNumber.set(nextReadNumber.get());
        }

        if (addTasks()) {
            return;
        }

        Hash256 hash;
        var hashFuture = client.execute(commands.getFinalizedHead());
        try {
            hash = hashFuture.get();
            LOGGER.debug("the finalized head is {}", hash);
        } catch (Throwable e) {
            LOGGER.error("get finalized head failed.", e);
            return;
        }

        var blockFuture = client.execute(commands.getBlock(hash));
        try {
            var block = blockFuture.get();
            LOGGER.debug("the finalized block number is {}", block.getBlock().getHeader().getNumber());
            var now = block.getBlock().getHeader().getNumber();
            var last = finalizedBlockNumber.get();
            if (now > last) {
                finalizedBlockNumber.set(now);
            }
        } catch (Throwable e) {
            LOGGER.error("get finalized block failed.", e);
        }

        addTasks();
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 1000)
    @Override
    public void saveBlock() throws ExecutionException, InterruptedException {
        while (true) {
            LOGGER.debug("block queue current size is {}", blockQueue.size());
            var result = blockQueue.peek();
            if (result == null) {
                break;
            }

            LOGGER.debug("current need save block number is {} and first block number is {}",
                    nextSaveNumber.get(), result.getNumber());
            if (result.getNumber() != nextSaveNumber.get()) {
                break;
            }

            LOGGER.debug("block {} is got", result.getNumber());
            updateExtrinsicReader(false);
            var saveResult = blockService.save(result.getNumber(), result.getHash(),
                    result.getBlock(), result.getEvent(), extrinsicReader.get(), eventsReader.get(), eventWriter.get());
            switch (saveResult) {
                case SUCCESS:
                    nextSaveNumber.incrementAndGet();
                    blockQueue.poll();
                    LOGGER.debug("block {} is saved", result.getNumber());
                    break;
                case UPDATE_METADATA:
                    updateExtrinsicReader(true);
                    break;
                case FAILED:
                default:
                    break;
            }
        }
    }

    private void updateExtrinsicReader(boolean force) throws ExecutionException, InterruptedException {
        if (extrinsicReader.get() != null && !force) {
            return;
        }

        var future = client.execute(commands.stateMetadata());
        var data = future.get();
        var meta = new MetadataReader().read(new ScaleCodecReader(data.getBytes()));
        extrinsicReader.set(new GeneralExtrinsicReader(meta, new FusotaoExtrinsicReaderRegistry(), SS58Type.Network.SUBSTRATE));
        eventReader.set(new EventReader(meta, new FusotaoEventReaderRegistry()));
        eventsReader.set(new EventsReader(meta, new FusotaoEventReaderRegistry()));
        eventWriter.set(new EventWriter(meta, new FusotaoEventWriterRegistry()));
    }

    @PostConstruct
    public void init() {
        commands = StandardCommands.getInstance();
        int cnt = Runtime.getRuntime().availableProcessors();
        int queueSize = MAX_SUBMIT;
        semaphore = new Semaphore(queueSize);
        queueSize *= 2;
        executor = new ThreadPoolExecutor(cnt, cnt * 4, 5, TimeUnit.MINUTES, new LinkedBlockingDeque<>(queueSize), r -> {
            var thread = new Thread(r);
            thread.setName("block-hash-getter-thread-" + theadIdx.getAndIncrement());
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
    }


    private class BlockResult implements Comparable<BlockResult> {
        private final long number;
        private Hash256 hash;
        private BlockJson block;
        private ByteData event;

        public BlockResult(long number) {
            this.number = number;
        }

        @Override
        public int compareTo(BlockResult o) {
            return Long.compare(this.number, o.number);
        }

        public long getNumber() {
            return number;
        }

        public Hash256 getHash() {
            return hash;
        }

        public void setHash(Hash256 hash) {
            this.hash = hash;
        }

        public BlockJson getBlock() {
            return block;
        }

        public void setBlock(BlockJson block) {
            this.block = block;
        }

        public ByteData getEvent() {
            return event;
        }

        public void setEvent(ByteData event) {
            this.event = event;
        }
    }

    private class Context {
        private State state;
        private BlockResult result;

        public Context(long number) {
            this.result = new BlockResult(number);
            state = new BlockHashGetState(this);
        }

        public void setState(State state) {
            this.state = state;
        }

        public void execute() {
            if (needExecute()) {
                state.execute();
            }
        }

        public boolean needExecute() {
            return state != endState;
        }

        public BlockResult getResult() {
            return result;
        }
    }

    private interface State {
        void execute();
    }

    private class BlockHashGetState implements State {
        private final Context context;

        public BlockHashGetState(Context context) {
            this.context = context;
        }

        @Override
        public void execute() {
            var number = context.getResult().getNumber();
            LOGGER.debug("start get {} hash", number);
            var future = client.execute(commands.getBlockHash(number));
            try {
                var hash = future.get();
                LOGGER.debug("the {} block hash is {}", number, hash);
                context.getResult().setHash(hash);
                context.setState(new BlockGetState(context));
            } catch (Throwable e) {
                LOGGER.error("get " + number + " block hash failed.", e);
            }
        }
    }

    private class BlockGetState implements State {
        private final Context context;

        public BlockGetState(Context context) {
            this.context = context;
        }

        @Override
        public void execute() {
            var result = context.getResult();
            var number = result.getNumber();
            var hash = result.getHash();
            LOGGER.debug("start get block {}", number);
            var future = client.execute(commands.getBlock(hash));
            try {
                var resp = future.get();
                var block = resp.getBlock();
                LOGGER.debug("block {} extrinsic count is {}", number, block.getExtrinsics().size());
                context.getResult().setBlock(block);
                context.setState(new EventGetState(context));
            } catch (Throwable e) {
                LOGGER.error("get " + number + " block failed.", e);
            }
        }
    }

    private class EventGetState implements State {
        private final ByteData key = ByteData.from("0x26aa394eea5630e07c48ae0c9558cef780d41e5e16056765bc8461851072c9d7");
        private final Context context;

        public EventGetState(Context context) {
            this.context = context;
        }

        @Override
        public void execute() {
            var result = context.getResult();
            var number = result.getNumber();
            var hash = result.getHash();
            LOGGER.debug("start get {} block event", number);
            var future = client.execute(
                    RpcCall.create(ByteData.class, PolkadotMethod.STATE_GET_STORAGE, key, hash));
            try {
                var data = future.get();
                if (data == null) {
                    LOGGER.debug("block {} event data is null", number);
                } else {
                    LOGGER.debug("block {} event data is {}", number, data.toString());
                }
                result.setEvent(data);
                context.setState(endState);
            } catch (Throwable e) {
                LOGGER.error("get " + number + " block event failed", e);
            }
        }
    }

    private class GetterRunnable implements Runnable {
        private final Context context;

        public GetterRunnable(long id) {
            context = new Context(id);
        }

        @Override
        public void run() {
            do {
                context.execute();
            } while (context.needExecute());

            do {
                if (blockQueue.add(context.getResult())) {
                    semaphore.release();
                    break;
                }

                Thread.yield();
            } while (true);
        }
    }

    public void decodeExtrinsic(byte[] data) {
        var reader = new ScaleCodecReader(data);
        var ext = extrinsicReader.get().read(reader);
    }
    public EventRecord decodeEvent(byte[] data) {
        var reader = new ScaleCodecReader(data);
        var event = eventReader.get().read(reader);
        return event;
    }

}
