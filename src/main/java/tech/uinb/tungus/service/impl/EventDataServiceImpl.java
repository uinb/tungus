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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.entity.EventData;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.repository.EventDataRepository;
import tech.uinb.tungus.repository.SeqRepository;
import tech.uinb.tungus.service.EventDataService;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class EventDataServiceImpl implements EventDataService {
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private SeqRepository seqRepository;
    @Autowired
    private EventDataRepository eventDataRepository;
    private LongHashSplitter splitter;

    @Override
    public long save(byte[] data) {
        var seq = seqRepository.queryByPrefix(TableMetaService.EVENT);
        var id = seq.incrementAndGet();
        var table = splitter.computeTable(id);
        eventDataRepository.save(id, data, table.tableName());
        seqRepository.update(seq);
        return id;
    }

    @Override
    public EventData getEventDataById(long id) {
        var table = splitter.computeTable(id);
        return eventDataRepository.queryEventId(id,table.tableName());
    }

    @PostConstruct
    public void init() {
        splitter = new LongHashSplitter(tableMetaService.getByPrefix(TableMetaService.EVENT));
    }
}
