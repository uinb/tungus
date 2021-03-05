package tech.uinb.tungus.service;

import java.util.List;

public interface BlockExtService {
    void save(long blkId, long extId);

    long getExtIndexInBlockByExtId(long extId);


}
