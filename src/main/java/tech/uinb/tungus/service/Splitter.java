package tech.uinb.tungus.service;

import tech.uinb.tungus.entity.TableDetail;

public interface Splitter<T> {
    void split();

    TableDetail computeTable(T key);

    TableDetail getBySuffix(int suffix);
}
