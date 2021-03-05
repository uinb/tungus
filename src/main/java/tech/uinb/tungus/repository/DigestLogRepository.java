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
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DigestLogRepository {
    @Autowired
    private JdbcTemplate template;

    public void saveList(long blkId, List<byte[]> datas, String tableName) {
        template.execute(con -> con.prepareStatement("insert into " + tableName + " (blk_id, idx, log) values (?, ?, ?)"),
                (PreparedStatementCallback<Object>) ps -> {
                    for (int i = 0; i < datas.size(); i++) {
                        ps.setLong(1, blkId);
                        ps.setInt(2, i);
                        ps.setBlob(3, new ByteArrayInputStream(datas.get(i)));
                        ps.addBatch();
                    }
                    ps.executeBatch();
                    return null;
                });
    }

    public void saveNull(long blkId, String tableName) {
        template.execute((ConnectionCallback<Void>) con -> {
            var stat = con.prepareStatement("insert into " + tableName + " (blk_id, idx, log) values (?, ?, ?)");
            stat.setLong(1, blkId);
            stat.setInt(2, 0);
            stat.setBlob(3, (Blob) null);
            stat.execute();
            return null;
        });
    }
}
