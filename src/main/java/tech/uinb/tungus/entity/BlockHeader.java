/**
 * Copyright 2021 UINB Technologies Pte. Ltd.
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.uinb.tungus.entity;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class BlockHeader {
    private Long blkId;
    private String extrinsics;
    private Long number;
    private String hash;
    private String parentHash;
    private String stateRoot;
    private List<DigestLog> logs;
    private Long createTime;
    private Long extrinsicsCnt;
    private Long eventsCnt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockHeader that = (BlockHeader) o;
        return Objects.equals(blkId, that.blkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blkId);
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "blkId=" + blkId +
                ", extrinsics='" + extrinsics + '\'' +
                ", number=" + number +
                ", parentHash='" + parentHash + '\'' +
                ", stateRoot='" + stateRoot + '\'' +
                '}';
    }
}
