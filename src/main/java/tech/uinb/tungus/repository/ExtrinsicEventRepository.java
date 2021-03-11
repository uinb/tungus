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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ExtrinsicEventRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(long extId, long eventId, String tableName) {
        template.execute("insert into " + tableName + " (ext_id, event_id) values (?, ?)", (PreparedStatementCallback<Void>) ps -> {
            ps.setLong(1, extId);
            ps.setLong(2, eventId);
            ps.execute();
            return null;
        });
    }

    public List<Long> queryListByExtId(long extId, String tableName) {
        return template.queryForList(
            "select event_id from " + tableName + " where ext_id = " + extId
                + " order by event_id desc", Long.class);
    }
}
