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

import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.entity.BlockExt;
import tech.uinb.tungus.repository.BlockExtRepository;
import tech.uinb.tungus.service.BlockExtService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlockExtServiceImpl implements BlockExtService {
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private BlockExtRepository blockExtRepository;
    private LongHashSplitter splitter;

    @Override
    public void save(long blkId, long extId) {
        var table = splitter.computeTable(blkId);
        blockExtRepository.save(blkId, extId, table.tableName());
    }

    @Override
    public String getExtIndexInBlockByExtId(long extId) {
        var table = splitter.computeTable(extId);
        long blkid = blockExtRepository.getBlkIdByExtId(extId,table.tableName());
        List<BlockExt> blockExtsId = blockExtRepository.getExtIdsByBlkId(blkid,table.tableName())
                .stream()
                .sorted(Comparator.comparingLong(BlockExt::getExtId))
                .collect(Collectors.toList());
        int index = 0;
        for (int i = 0; i < blockExtsId.size(); i++) {
            if (blockExtsId.get(i).getExtId() == extId){
                index = i;
            }
        }
        return blockExtsId.get(index).getBlkId()+"-"+index;
    }

    @Override
    public long getBlockIdByExtId(long extId) {
        var table = splitter.computeTable(extId);
        return blockExtRepository.getBlkIdByExtId(extId,table.tableName());
    }

    @Override
    public long getFirstExtIdByExtId(long id) {
        var table = splitter.computeTable(id);
        long bId = blockExtRepository.getBlkIdByExtId(id,table.tableName());
        return blockExtRepository.getExtIdsByBlkId(bId,table.tableName()).get(0).getExtId();
    }

    @PostConstruct
    public void init() {
        splitter = new LongHashSplitter(tableMetaService.getByPrefix(TableMetaService.BLOCK_EXTRINSIC));
    }
}
