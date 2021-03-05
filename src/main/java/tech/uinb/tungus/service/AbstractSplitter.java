package tech.uinb.tungus.service;

import tech.uinb.tungus.entity.TableDetail;
import tech.uinb.tungus.entity.TableMeta;
import tech.uinb.tungus.entity.SyncState;

import java.math.BigInteger;

public abstract class AbstractSplitter<T> implements Splitter<T> {
    protected final TableMeta meta;

    public AbstractSplitter(TableMeta meta) {
        this.meta = meta;
    }

    @Override
    public void split() {
        var details = meta.getDetails();
        int size = details.size();
        for (int i = 0; i < size; i++) {
            var backend = details.get(i);
            backend.setState(SyncState.COPY);
            long mid = BigInteger.valueOf(backend.getMin().longValue())
                    .add(BigInteger.valueOf(backend.getMax().longValue()))
                    .divide(BigInteger.valueOf(2)).longValue();
            backend.setMax(mid - 1);

            var detail = new TableDetail();
            detail.setPrefix(backend.getPrefix());
            detail.setSuffix(i + size);
            detail.setBackend(i);
            detail.setMin(mid);
            detail.setMax(backend.getMax());
            detail.setState(SyncState.COPY);
            details.add(detail);
        }

        meta.sortDetails();
    }

    protected TableDetail search(long key) {
        var details = meta.getDetails();
        int start = 0;
        int end = details.size() - 1;
        while (true) {
            if (start == end) {
                var detail = details.get(start);
                if (detail.contains(key) == 0) {
                    return detail;
                }
                throw new RuntimeException("can not find actual table");
            }

            int mid = (start + end) / 2;
            var detail = details.get(mid);
            int ret = detail.contains(key);
            if (ret == 0) {
                return detail;
            } else if (ret > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            if (end < start) {
                throw new RuntimeException("can not find actual table");
            }
        }
    }
}
