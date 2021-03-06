package tech.uinb.tungus.service;

import java.util.List;
import tech.uinb.tungus.codec.EventWriter;
import tech.uinb.tungus.codec.EventsReader;
import tech.uinb.tungus.codec.GeneralExtrinsicReader;
import io.emeraldpay.polkaj.json.BlockJson;
import io.emeraldpay.polkaj.types.ByteData;
import io.emeraldpay.polkaj.types.Hash256;
import tech.uinb.tungus.entity.BlockHeader;
import tech.uinb.tungus.entity.Ext;

public interface BlockService {
    /**
     * tallest block number
     *
     * @return
     */
    Long lastBlockNumber();


    SaveResult save(long number, Hash256 hash, BlockJson block,
                    ByteData events,
                    GeneralExtrinsicReader extrinsicReader,
                    EventsReader eventsReader,
                    EventWriter eventWriter);

    enum SaveResult {
        SUCCESS, UPDATE_METADATA, FAILED
    }

    BlockHeader getBlockHeaderById(long id);

    Ext getExtById(long id);

    List<BlockHeader> getBlockByIds(List<Long> ids);

    List<Ext> getExtByIds(List<Long> ids);

    Long lastExtNumber();

    long getTimestampByExtId(long ext_id);

}
