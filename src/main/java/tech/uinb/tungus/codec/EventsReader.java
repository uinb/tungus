package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

import java.util.List;

public class EventsReader {
    private final Metadata metadata;
    private final ReaderRegistry readerRegistry;
    private ListReader<EventRecord> reader;

    public EventsReader(Metadata metadata, ReaderRegistry readerRegistry) {
        this.metadata = metadata;
        this.readerRegistry = readerRegistry;
        reader = new ListReader(new EventReader(metadata, readerRegistry));
    }

    public List<EventRecord> read(ScaleCodecReader rdr) {
        return reader.read(rdr);
    }
}
