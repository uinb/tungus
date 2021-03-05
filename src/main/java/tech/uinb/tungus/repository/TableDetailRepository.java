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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.TableDetail;
import tech.uinb.tungus.entity.SyncState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TableDetailRepository {
    @Autowired
    private JdbcTemplate template;

    public List<TableDetail> queryByPrefix(String prefix) {
        return template.query(conn -> {
            var stat = conn.prepareStatement("select * from t_table_detail where prefix = ?");
            stat.setString(1, prefix);
            return stat;
        }, new TableDetailRowMapper());
    }

    private class TableDetailRowMapper implements RowMapper<TableDetail> {
        @Override
        public TableDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            TableDetail detail = new TableDetail();
            detail.setPrefix(rs.getString("prefix"));
            detail.setSuffix(rs.getInt("suffix"));
            detail.setBackend(rs.getInt("backend"));
            if (rs.wasNull()) {
                detail.setBackend(null);
            }
            detail.setMin(rs.getLong("min"));
            detail.setMax(rs.getLong("max"));
            detail.setState(SyncState.valueOf(rs.getString("state")));
            return detail;
        }
    }
}
