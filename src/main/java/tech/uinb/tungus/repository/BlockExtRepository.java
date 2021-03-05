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
package tech.uinb.tungus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.BlockExt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BlockExtRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(long blkId, long extId, String tableName) {
        template.execute("insert into " + tableName + " (blk_id, ext_id) values (?, ?)",
                (PreparedStatementCallback<Void>) ps -> {
                    ps.setLong(1, blkId);
                    ps.setLong(2, extId);
                    ps.execute();
                    return null;
                });
    }

    public long getBlkIdByExtId(long extId, String tableName) {
        return template.queryForObject("select blk_id from " + tableName + " where ext_id = " + extId, Long.class);
    }

    public List<BlockExt> getExtIdsByBlkId(long blkId, String tableName) {
        return template.query("select * from " + tableName + " where blk_Id = " + blkId,new ExtRowMapper());
    }

    class ExtRowMapper implements RowMapper<BlockExt> {

        @Override
        public BlockExt mapRow(ResultSet rs, int rowNum) throws SQLException {
            BlockExt blockExt = new BlockExt();
            blockExt.setBlkId(rs.getLong("blk_id"));
            blockExt.setExtId(rs.getLong("ext_id"));
            return blockExt;
        }
    }
}