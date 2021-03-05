package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UInt64Reader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class EventReader implements ScaleReader<EventRecord> {
    private final static UInt64Reader UINT64 = new UInt64Reader();
    private final Metadata metadata;
    private final ReaderRegistry readerRegistry;
    private ConcurrentHashMap<String, String> index2Name = new ConcurrentHashMap<>();

    public EventReader(Metadata metadata, ReaderRegistry readerRegistry) {
        this.metadata = metadata;
        this.readerRegistry = readerRegistry;
    }

    @Override
    public EventRecord read(ScaleCodecReader rdr) {
        Integer phase = rdr.readUByte();
        Long extrinsicIdx = -1L;
        if (phase == 0) {
            extrinsicIdx = rdr.readUint32();
        }

        int moduleIndex = rdr.readUByte();
        int eventIndex = rdr.readUByte();
        String index = moduleIndex + "-" + eventIndex;
        var name = index2Name.get(index);
        ScaleReader reader = null;
        if (name == null) {
            var nameOpt = getEventFullName(moduleIndex, eventIndex);
            if (nameOpt.isEmpty()) {
                throw new IllegalStateException("metadata not contains Event(" + moduleIndex + "," + eventIndex + ")");
            }
            name = nameOpt.get();
            index2Name.putIfAbsent(index, name);
        }
        reader = readerRegistry.getReader(name);
        if (reader == null) {
            throw new IllegalStateException("not contains " + name + "Reader");
        }

        EventRecord event = (EventRecord) reader.read(rdr);
        event.setModuleIndex(moduleIndex);
        event.setEventIndex(eventIndex);
        event.setPhase(phase);
        event.setExtrinsicIdx(extrinsicIdx);
        event.setTopics(rdr.readUByte());
        return event;
    }


    private Optional<String> getEventFullName(int moduleIndex, int eventIndex) {
        var modules = metadata.getModules();
        if (modules == null || modules.isEmpty()) {
            return Optional.empty();
        }

        for (Metadata.Module module : modules) {
            if (module.getIndex() == moduleIndex) {
                var events = module.getEvents();
                if (events == null || events.isEmpty()) {
                    return Optional.empty();
                }

                if (events.isEmpty() || eventIndex >= events.size()) {
                    throw new IllegalStateException("metadata not contains Event(" + moduleIndex + "," + eventIndex + ")");
                }

                return Optional.of(module.getName() + "." + events.get(eventIndex).getName());
            }
        }

        return Optional.empty();
    }
}
