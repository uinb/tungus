package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UInt64Writer;
import io.emeraldpay.polkaj.scaletypes.Metadata;

import java.io.IOException;

public class EventWriter implements ScaleWriter<EventRecord> {
    private final static UInt64Writer UINT64 = new UInt64Writer();
    private final Metadata metadata;
    private final WriterRegistry writerRegistry;

    public EventWriter(Metadata metadata, WriterRegistry writerRegistry) {
        this.metadata = metadata;
        this.writerRegistry = writerRegistry;
    }

    @Override
    public void write(ScaleCodecWriter wrt, EventRecord value) throws IOException {
        Integer phase = value.getPhase();
        wrt.writeByte(phase);
        if (phase == 0) {
            wrt.writeUint32(value.getExtrinsicIdx());
        }
        wrt.writeByte(value.getModuleIndex());
        wrt.writeByte(value.getEventIndex());
        wrt.write(writerRegistry.getWriter(value.getClass()), value);
        wrt.writeByte(value.getTopics());
    }
}
