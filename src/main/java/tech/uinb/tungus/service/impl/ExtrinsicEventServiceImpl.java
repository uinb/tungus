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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.repository.ExtrinsicEventRepository;
import tech.uinb.tungus.service.ExtrinsicEventService;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class ExtrinsicEventServiceImpl implements ExtrinsicEventService {
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private ExtrinsicEventRepository extrinsicEventRepository;
    private LongHashSplitter splitter;

    @Override
    public void save(long extId, long eventId) {
        var table = splitter.computeTable(extId);
        extrinsicEventRepository.save(extId, eventId, table.tableName());
    }

    @Override
    public List<Long> getEventIdByExtId(long extId) {
        var table = splitter.computeTable(extId);
        return extrinsicEventRepository.queryListByExtId(extId,table.tableName());
    }

    @PostConstruct
    public void init() {
        splitter = new LongHashSplitter(tableMetaService.getByPrefix(TableMetaService.EXTRINSIC_EVENT));
    }
}
