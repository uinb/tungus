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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.BlockHeader;

@Repository
public class BlockHeaderRepository {

  @Autowired
  private JdbcTemplate template;

  public Long queryMaxId(String tableName) {
    return template.queryForObject("select max(blk_id) from " + tableName, Long.class);
  }

  public void save(Long blkId, String extrinsics, Long number,
      String parentHash, String stateRoot, String tableName) {
    template.execute((ConnectionCallback<Void>) con -> {
      var stat = con.prepareStatement("insert into " + tableName
          + " (blk_id, extrinsics, number, parent_hash, state_root) values (?, ?, ?, ?, ?)");
      stat.setLong(1, blkId);
      stat.setString(2, extrinsics);
      stat.setLong(3, number);
      stat.setString(4, parentHash);
      stat.setString(5, stateRoot);
      stat.execute();
      return null;
    });
  }

  public BlockHeader queryByBlockId(long bId,String tableName){
    return template.queryForObject("select * from" + tableName + " where blk_id = " + bId,BlockHeader.class);
  }

}
