package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UInt64Writer;
import io.emeraldpay.polkaj.scaletypes.EraWriter;
import io.emeraldpay.polkaj.scaletypes.Extrinsic;
import io.emeraldpay.polkaj.scaletypes.ExtrinsicCall;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class GeneralExtrinsicWriter<CALL extends ExtrinsicCall> implements ScaleWriter<Extrinsic<CALL>> {
    private final static UInt64Writer UINT64 = new UInt64Writer();

    private static final TransactionInfoWriter TX_WRITER = new TransactionInfoWriter();

    private final WriterRegistry writerRegistry;

    public GeneralExtrinsicWriter(WriterRegistry writerRegistry) {
        this.writerRegistry = writerRegistry;
    }

    @Override
    public void write(ScaleCodecWriter wrt, Extrinsic<CALL> value) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ScaleCodecWriter internal = new ScaleCodecWriter(buf);
        if (value.getTx() != null) {
            int type = Extrinsic.TYPE_BIT_SIGNED + (Extrinsic.TYPE_UNMASK_VERSION & 4);
            internal.writeByte(type);
            internal.write(TX_WRITER, value.getTx());
        } else {
            int type = Extrinsic.TYPE_UNMASK_VERSION & 4;
            internal.writeByte(type);
        }
        var call = value.getCall();
        wrt.writeByte(call.getModuleIndex());
        wrt.writeByte(call.getCallIndex());
        var writer = writerRegistry.getWriter(call.getClass());
        if (writer == null) {
            throw new IllegalStateException("not exists " + writer.getClass() + "Writer");
        }

        internal.write(writer, value.getCall());
        // the extrinsic itself is written as array, so the body of it can be processed individually as bytes
        wrt.writeAsList(buf.toByteArray());
    }

    static class TransactionInfoWriter implements ScaleWriter<Extrinsic.TransactionInfo> {

        private static final EraWriter ERA_WRITER = new EraWriter();

        @Override
        public void write(ScaleCodecWriter wrt, Extrinsic.TransactionInfo value) throws IOException {
            wrt.writeUint256(value.getSender().getPubkey());
            wrt.writeByte(Extrinsic.SignatureType.SR25519.getCode());
            wrt.writeByteArray(value.getSignature().getValue().getBytes());
            wrt.write(ERA_WRITER, value.getEra());
            wrt.write(ScaleCodecWriter.COMPACT_BIGINT, BigInteger.valueOf(value.getNonce()));
            wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getTip().getValue());
        }
    }
}
