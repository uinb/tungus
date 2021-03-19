/**
 * Copyright 2021 UINB Technologies Pte. Ltd.
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.uinb.tungus.service.impl;

import io.emeraldpay.polkaj.json.BlockJson;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;
import io.emeraldpay.polkaj.tx.Hashing;
import io.emeraldpay.polkaj.types.ByteData;
import io.emeraldpay.polkaj.types.Hash256;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.EventWriter;
import tech.uinb.tungus.codec.EventsReader;
import tech.uinb.tungus.codec.GeneralExtrinsicReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.*;
import tech.uinb.tungus.entity.BlockHeader;
import tech.uinb.tungus.entity.Ext;
import tech.uinb.tungus.entity.ObjType;
import tech.uinb.tungus.repository.BlockHeaderRepository;
import tech.uinb.tungus.repository.DigestLogRepository;
import tech.uinb.tungus.repository.ExtRepository;
import tech.uinb.tungus.repository.SeqRepository;
import tech.uinb.tungus.service.*;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceImpl.class);
    @Autowired
    private BlockHeaderRepository blockHeaderRepository;
    @Autowired
    private DigestLogRepository digestLogRepository;
    @Autowired
    private SeqRepository seqRepository;
    @Autowired
    private ExtRepository extRepository;
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private HashIdMapService hashIdMapService;
    @Autowired
    private AccountExtrinsicService accountExtrinsicService;
    @Autowired
    private AccountTransactionService accountTransactionService;
    @Autowired
    private AccountStashService accountStashService;
    @Autowired
    private BlockExtService blockExtService;
    @Autowired
    private EventDataService eventDataService;
    @Autowired
    private ExtrinsicEventService extrinsicEventService;
    @Autowired
    private StashExtService stashExtService;
    @Autowired
    private TransferExtService transferExtService;
    @Autowired
    private PledgeExtService pledgeExtService;

    private LongSeqSplitter headerSplitter;
    private LongSeqSplitter digestLogSplitter;
    private LongSeqSplitter extrinsicSplitter;
    private GeneralProcessor generalProcessor;

    private Map<Class<? extends ExtrinsicCall>, ExtrinsicProcessor> processorMap;

    @Override
    public Long lastBlockNumber() {
        var meta = tableMetaService.getByPrefix(TableMetaService.BLOCK_HEADER);
        var details = meta.getDetails();
        for (int i = details.size() - 1; i >= 0; i--) {
            var detail = details.get(i);
            var id = blockHeaderRepository.queryMaxId(detail.tableName());
            if (id != null) {
                return id;
            }
        }
        return null;
    }

    @Override
    public SaveResult save(long number, Hash256 hash,
                           BlockJson block,
                           ByteData eventsData,
                           GeneralExtrinsicReader extrinsicReader,
                           EventsReader eventsReader,
                           EventWriter eventWriter) {
        hashIdMapService.save(hash.toString(), number, ObjType.BLOCK);
        var header = block.getHeader();
        var digest = header.getDigest();
        if (digest != null) {
            var logs = digest.getLogs();
            var detail = digestLogSplitter.computeTable(number);
            if (logs != null && !logs.isEmpty()) {
                digestLogRepository.saveList(number,
                        logs.stream().map(ByteData::getBytes).collect(Collectors.toList()),
                        detail.tableName());
            } else {
                digestLogRepository.saveNull(number, detail.tableName());
            }
        }

        var extrinsics = block.getExtrinsics();
        Map<Long, ExtrinsicEventInfo> extEventMap = new HashMap<>();
        long createTime = 0L;
        long extrinsicsCnt = 0L;
        if (extrinsics != null && !extrinsics.isEmpty()) {
            extrinsicsCnt = extrinsics.size();
            var seq = seqRepository.queryByPrefix(TableMetaService.EXTRINSICS);
            for (int i = 0; i < extrinsics.size(); i++) {
                var extrinsic = extrinsics.get(i);

                if (i == 0) {
                    var ext = extrinsicReader.read(new ScaleCodecReader(extrinsic.getBytes()));
                    FusotaoTimestampSet time = (FusotaoTimestampSet) ext.getCall();
                    createTime = time.getNow().longValue();
                }

                var id = seq.incrementAndGet();
                var data = extrinsic.getBytes();
                extRepository.save(id, data, extrinsicSplitter.computeTable(id).tableName());
                var extrinsicHash = new Hash256(Hashing.blake2(data));
                hashIdMapService.save(extrinsicHash.toString(), id, ObjType.EXTRINSICS);
                decodeExtrinsic(extrinsicReader, extrinsic.getBytes(), id);
                blockExtService.save(number, id);
                extEventMap.put((long) i, new ExtrinsicEventInfo(id));
            }
            seqRepository.update(seq);
        }

        long eventsCnt = 0L;
        if (eventsData != null) {
            var reader = new ScaleCodecReader(eventsData.getBytes());
            var events = eventsReader.read(reader);
            if (events != null && !events.isEmpty()) {
                eventsCnt = events.size();
                for (EventRecord event : events) {
                    var eventId = saveEvent(eventWriter, event);
                    var extIdx = event.getExtrinsicIdx();
                    var info = extEventMap.get(extIdx);
                    if (info != null) {
                        info.eventId.add(eventId);
                    }
                }

                for (Map.Entry<Long, ExtrinsicEventInfo> entry : extEventMap.entrySet()) {
                    var id = entry.getValue().id;
                    entry.getValue().eventId.forEach(eventId -> {
                        extrinsicEventService.save(id, eventId);
                    });
                }
            }
        }

        blockHeaderRepository.save(number,
                header.getExtrinsicsRoot().toString(),
                header.getNumber(),
                hash.toString(),
                header.getParentHash().toString(),
                header.getStateRoot().toString(),
                createTime,
                extrinsicsCnt,
                eventsCnt,
                headerSplitter.computeTable(number).tableName());

        return SaveResult.SUCCESS;
    }

    @Override
    public BlockHeader getBlockHeaderById(long id) {
        var table = headerSplitter.computeTable(id);
        return blockHeaderRepository.queryByBlockId(id, table.tableName());
    }

    @Override
    public Ext getExtById(long id) {
        var table = extrinsicSplitter.computeTable(id);
        return extRepository.queryExtId(id, table.tableName());
    }

    @Override
    public List<BlockHeader> getBlockByIds(List<Long> ids) {
        Splitter splitter = new LongHashSplitter(tableMetaService.getByPrefix(TableMetaService.BLOCK_HEADER));
        var table_start = splitter.computeTable(ids.get(0));
        var table_end = splitter.computeTable(ids.get(ids.size()-1));
        List list = null;
        if (table_end.equals(table_start)){
            list = blockHeaderRepository.queryBlockByIds(ids,table_start.tableName());
        }else {
            for (int i = 0; i < ids.size(); i++) {
                list = new ArrayList();
                list.add(getBlockHeaderById(ids.get(i)));
            }
        }
        return list;
    }

    @Override
    public List<Ext> getExtByIds(List<Long> ids) {
        Splitter splitter = new LongHashSplitter(tableMetaService.getByPrefix(TableMetaService.EXTRINSICS));
        var table_start = splitter.computeTable(ids.get(0));
        var table_end = splitter.computeTable(ids.get(ids.size()-1));
        List<Ext> list = null;
        if (table_end.equals(table_start)){
            list = extRepository.queryExtByIds(ids,table_start.tableName());
        }else {
            for (int i = 0; i < ids.size(); i++) {
                list = new ArrayList();
                list.add(getExtById(ids.get(i)));
            }
        }
        return list;
    }

    @Override
    public Long lastExtNumber() {
        var seq = seqRepository.queryByPrefix(TableMetaService.EXTRINSICS);
        return seq.getValue();
    }

    @Override
    public long getTimestampByExtId(long ext_id) {
        return getBlockHeaderById(Long.parseLong(blockExtService.getExtIndexInBlockByExtId(ext_id).split("-")[0])).getCreateTime();
    }

    private long saveEvent(EventWriter writer, EventRecord event) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ScaleCodecWriter wrt = new ScaleCodecWriter(out);
        try {
            writer.write(wrt, event);
            return eventDataService.save(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void decodeExtrinsic(GeneralExtrinsicReader extrinsicReader, byte[] data, long extId) {
        LOGGER.debug("Extrinsic data is 0x{}", Hex.toHexString(data));
        var reader = new ScaleCodecReader(data);
        var ext = extrinsicReader.read(reader);
        generalProcessor.process(ext, extId);
        var call = ext.getCall();
        var processor = processorMap.get(call.getClass());
        if (processor != null) {
            processor.process(ext, extId);
        }
    }

    @PostConstruct
    public void init() {
        headerSplitter = new LongSeqSplitter(tableMetaService.getByPrefix(TableMetaService.BLOCK_HEADER));
        digestLogSplitter = new LongSeqSplitter(tableMetaService.getByPrefix(TableMetaService.DIGEST_LOG));
        extrinsicSplitter = new LongSeqSplitter(tableMetaService.getByPrefix(TableMetaService.EXTRINSICS));
        generalProcessor = new GeneralProcessor();
        processorMap = new HashMap<>();
        processorMap.put(FusotaoBalancesTransfer.class, new BalanceTransferProcessor());
        processorMap.put(FusotaoBalancesTransferKeepAlive.class, new BalanceTransferKeepAliveProcessor());
        processorMap.put(FusotaoReceiptsMergeToAddStash.class, new StashProcessor());
        processorMap.put(FusotaoReceiptsMergeToAddStashToken.class, new StashProcessor());
        processorMap.put(FusotaoReceiptsMergeToDeductStash.class, new StashProcessor());
        processorMap.put(FusotaoReceiptsMergeToDeductStashToken.class, new StashProcessor());
    }

    private interface ExtrinsicProcessor {
        void process(Extrinsic ext, long id);
    }

    private class GeneralProcessor implements ExtrinsicProcessor {
        @Override
        public void process(Extrinsic ext, long id) {
            var tx = ext.getTx();
            if (tx != null) {
                var sender = tx.getSender();
                long accountId = getAccountId(sender.toString());
                accountExtrinsicService.save(accountId, id);
            }
        }
    }

    private class BalanceTransferProcessor implements ExtrinsicProcessor {
        @Override
        public void process(Extrinsic ext, long id) {
            var tx = ext.getTx();
            var sender = tx.getSender();
            save(sender.toString(), id);

            FusotaoBalancesTransfer balance = (FusotaoBalancesTransfer) ext.getCall();
            var destination = balance.getDest();
            save(destination.toString(), id);

            transferExtService.save(id);
        }

        private void save(String account, long id) {
            var accountId = getAccountId(account);
            accountTransactionService.save(accountId, id);
        }
    }

    private class BalanceTransferKeepAliveProcessor implements ExtrinsicProcessor {
        @Override
        public void process(Extrinsic ext, long id) {
            var tx = ext.getTx();
            var sender = tx.getSender();
            save(sender.toString(), id);

            FusotaoBalancesTransferKeepAlive balance = (FusotaoBalancesTransferKeepAlive) ext.getCall();
            var destination = balance.getDest();
            save(destination.toString(), id);

            transferExtService.save(id);
        }

        private void save(String account, long id) {
            var accountId = getAccountId(account);
            accountTransactionService.save(accountId, id);
        }
    }

    private class StashProcessor implements ExtrinsicProcessor {
        @Override
        public void process(Extrinsic ext, long id) {
            var tx = ext.getTx();
            var sender = tx.getSender();
            save(sender.toString(), id);

            stashExtService.save(id);
        }

        private void save(String account, long id) {
            long accountId = getAccountId(account);
            accountStashService.save(accountId, id);
        }
    }

    private long getAccountId(String account) {
        var hashIdMap = hashIdMapService.getByHash(account);
        long accountId;
        if (hashIdMap == null) {
            var seq = seqRepository.queryByPrefix(TableMetaService.ACCOUNT);
            accountId = seq.incrementAndGet();
            hashIdMapService.save(account, accountId, ObjType.ACCOUNT);
            seqRepository.update(seq);
        } else {
            accountId = hashIdMap.getId();
        }
        return accountId;
    }

    private class ExtrinsicEventInfo {
        final Long id;
        final ArrayList<Long> eventId = new ArrayList<>();

        ExtrinsicEventInfo(Long id) {
            this.id = id;
        }
    }
}
