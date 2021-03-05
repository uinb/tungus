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
package tech.uinb.tungus.entity;

import java.util.Objects;

public class TableDetail {
    private String prefix;
    private Integer suffix;
    private Integer backend;
    private Long min;
    private Long max;
    private SyncState state;

    public String tableName() {
        return prefix + "_" + suffix;
    }

    public int contains(long suffix) {
        if (suffix < min) {
            return 1;
        }

        if (suffix > max) {
            return -1;
        }

        return 0;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getSuffix() {
        return suffix;
    }

    public void setSuffix(Integer suffix) {
        this.suffix = suffix;
    }

    public Integer getBackend() {
        return backend;
    }

    public void setBackend(Integer backend) {
        this.backend = backend;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public SyncState getState() {
        return state;
    }

    public void setState(SyncState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableDetail that = (TableDetail) o;
        return Objects.equals(prefix, that.prefix) && Objects.equals(suffix, that.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, suffix);
    }

    @Override
    public String toString() {
        return "TableDetail{" +
                "prefix='" + prefix + '\'' +
                ", suffix=" + suffix +
                ", backend=" + backend +
                ", min=" + min +
                ", max=" + max +
                ", state=" + state +
                '}';
    }
}
