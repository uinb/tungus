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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.TableMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TableMetaRepository {
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private TableDetailRepository tableDetailRepository;

    private final TableMetaRowMapper mapper = new TableMetaRowMapper();
    private final TableMetaExtractor extractor = new TableMetaExtractor();

    public TableMeta getByPrefix(String prefix) {
        var meta = template.query(con -> {
            var stat = con.prepareStatement("select * from t_table_meta where prefix = ?");
            stat.setString(1, prefix);
            return stat;
        }, extractor);
        var details = tableDetailRepository.queryByPrefix(prefix);
        meta.setDetails(new ArrayList<>(details));
        meta.sortDetails();
        return meta;
    }

    private class TableMetaRowMapper implements RowMapper<TableMeta> {
        @Override
        public TableMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
            TableMeta meta = new TableMeta();
            meta.setPrefix(rs.getString("prefix"));
            meta.setVersion(rs.getLong("version"));
            meta.setSplitting(rs.getBoolean("splitting"));
            return meta;
        }
    }

    private class TableMetaExtractor implements ResultSetExtractor<TableMeta> {
        @Override
        public TableMeta extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                TableMeta meta = new TableMeta();
                meta.setPrefix(rs.getString("prefix"));
                meta.setVersion(rs.getLong("version"));
                meta.setSplitting(rs.getBoolean("splitting"));
                return meta;
            } else {
                return null;
            }
        }
    }
}
