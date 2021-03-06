package tech.uinb.tungus.service;


public interface BlockExtService {
    void save(long blkId, long extId);

    String getExtIndexInBlockByExtId(long extId);


}
