/**
 * Copyright 2021 UINB Technologies Pte. Ltd.
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.  You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package tech.uinb.tungus.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.BlockHeader;

@Repository
public class BlockHeaderRepository {

    @Autowired
    private JdbcTemplate template;

    public Long queryMaxId(String tableName) {
        return template.queryForObject("select max(blk_id) from " + tableName, Long.class);
    }

    public void save(Long blkId, String extrinsics, Long number,String hash ,String parentHash,
                     String stateRoot, Long createTime, Long extrinsicsCnt, Long eventsCnt,
                     String tableName) {
        template.execute((ConnectionCallback<Void>) con -> {
            var stat = con.prepareStatement("insert into " + tableName
                    + " (blk_id, extrinsics, number, hash ,parent_hash, state_root, create_time, extrinsics_cnt, events_cnt) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stat.setLong(1, blkId);
            stat.setString(2, extrinsics);
            stat.setLong(3, number);
            stat.setString(4,hash);
            stat.setString(5, parentHash);
            stat.setString(6, stateRoot);
            stat.setLong(7, createTime);
            stat.setLong(8, extrinsicsCnt);
            stat.setLong(9, eventsCnt);
            stat.execute();
            return null;
        });
    }

    public BlockHeader queryByBlockId(long bId, String tableName) {
        return template.queryForObject("select * from " + tableName + " where blk_id = " + bId, new BlockHeaderRowMapper());
    }

    public List<BlockHeader> queryBlockByIds(List<Long> ids,String tableName) {
        StringBuffer bId = new StringBuffer();
        for (int i = 0; i < ids.size(); i++) {
            bId.append(ids.get(i).toString());
            if (i!=ids.size()-1){
                bId.append(",");
            }
        }
        String in = "("+bId+")";
        return template.query("select * from " + tableName + " where blk_id in " + in +" ORDER BY blk_id DESC ",new BlockHeaderRowMapper());
    }

    class BlockHeaderRowMapper implements RowMapper<BlockHeader> {

        @Override
        public BlockHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
            BlockHeader blockHeader = new BlockHeader();
            blockHeader.setBlkId(rs.getLong("blk_id"));
            blockHeader.setNumber(rs.getLong("number"));
            blockHeader.setCreateTime(rs.getLong("create_time"));
            blockHeader.setEventsCnt(rs.getLong("events_cnt"));
            blockHeader.setExtrinsics(rs.getString("extrinsics"));
            blockHeader.setParentHash(rs.getString("parent_hash"));
            blockHeader.setStateRoot(rs.getString("state_root"));
            blockHeader.setExtrinsicsCnt(rs.getLong("extrinsics_cnt"));
            blockHeader.setHash(rs.getString("hash"));
            return blockHeader;
        }
    }
}
