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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountExtrinsicsRepository {

  @Autowired
  private JdbcTemplate template;

  public void save(long accountId, long extId, String tableName) {
    template.execute((ConnectionCallback<Void>) con -> {
      var stat = con
          .prepareStatement("insert into " + tableName + " (account_id, ext_id) values (?, ?)");
      stat.setLong(1, accountId);
      stat.setLong(2, extId);
      stat.execute();
      return null;
    });
  }

  public List<Long> queryListByAccount(long accountId, String tableName) {
    return template.queryForList(
        "select ext_id from " + tableName + " where account_id = " + accountId
            + " order by ext_id desc", Long.class);
  }
}
