package tech.uinb.tungus.service;

import tech.uinb.tungus.entity.HashIdMap;
import tech.uinb.tungus.entity.ObjType;

public interface HashIdMapService {
    void save(String key, long id, ObjType type);

    HashIdMap getByHash(String hash);

    HashIdMap getByBlockNumber(long id);

}
