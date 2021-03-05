package tech.uinb.tungus.service.impl;

import tech.uinb.tungus.entity.TableDetail;
import tech.uinb.tungus.entity.TableMeta;
import tech.uinb.tungus.service.AbstractSplitter;

public class StringSplitter extends AbstractSplitter<String> {

    public StringSplitter(TableMeta meta) {
        super((meta));
    }

    @Override
    public TableDetail computeTable(String key) {
        long hash = 0L;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        hash = Math.abs(hash);
        return search(hash);
    }
}
