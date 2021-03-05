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

import java.util.*;

public class TableMeta {
    private String prefix;
    private Long version;
    private Boolean splitting;
    private List<TableDetail> details = new ArrayList<>();

    public void sortDetails() {
        Collections.sort(details, Comparator.comparingInt(TableDetail::getSuffix));
    }

    public TableDetail getBackendTable(TableDetail detail) {
        return details.get(detail.getBackend());
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Boolean getSplitting() {
        return splitting;
    }

    public void setSplitting(Boolean splitting) {
        this.splitting = splitting;
    }

    public List<TableDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TableDetail> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableMeta tableMeta = (TableMeta) o;
        return Objects.equals(prefix, tableMeta.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix);
    }

    @Override
    public String toString() {
        return "TableMeta{" +
                "prefix='" + prefix + '\'' +
                ", version=" + version +
                ", splitting=" + splitting +
                '}';
    }
}
