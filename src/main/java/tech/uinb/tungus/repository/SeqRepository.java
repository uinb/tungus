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
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.Seq;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SeqRepository {
    private final SeqResultExtractor extractor = new SeqResultExtractor();
    @Autowired
    private JdbcTemplate template;

    public Seq queryByPrefix(String prefix) {
        return template.query(con -> {
            var stat = con.prepareStatement("select * from t_seq where prefix = ?");
            stat.setString(1, prefix);
            return stat;
        }, extractor);
    }

    public void update(Seq seq) {
        template.update("update t_seq set value = ? where prefix = ?", seq.getValue(), seq.getPrefix());
    }

    private class SeqResultExtractor implements ResultSetExtractor<Seq> {

        @Override
        public Seq extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                Seq seq = new Seq();
                seq.setPrefix(rs.getString("prefix"));
                seq.setValue(rs.getLong("value"));
                return seq;
            }
            return null;
        }
    }
}
