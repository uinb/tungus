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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import tech.uinb.tungus.entity.EventData;
import tech.uinb.tungus.entity.Ext;

@Repository
public class EventDataRepository {

  @Autowired
  private JdbcTemplate template;

  public void save(long id, byte[] data, String tableName) {
    template.execute("insert into " + tableName + " (id, data) values (?, ?)",
        (PreparedStatementCallback<Void>) ps -> {
          ps.setLong(1, id);
          ps.setBlob(2, new ByteArrayInputStream(data));
          ps.execute();
          return null;
        });
  }

  public EventData queryEventId(long id, String tableName) {
    return template.query(con -> {
      var stat = con.prepareStatement("select * from " + tableName + " where id = ?");
      stat.setLong(1, id);
      return stat;
    }, extractor);
  }

  private final EventResultExtractor extractor = new EventDataRepository.EventResultExtractor();

  private class EventResultExtractor implements ResultSetExtractor<EventData> {

    @Override
    public EventData extractData(ResultSet rs) throws SQLException, DataAccessException {
      if (rs.next()) {
        EventData eventData = new EventData();
        eventData.setData(rs.getBytes("data"));
        eventData.setId(rs.getLong("id"));
        return eventData;
      }
      return null;
    }
  }
}
