package tech.uinb.tungus.codec.fusotao.type.writer;

import tech.uinb.tungus.codec.fusotao.type.DispatchInfo;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UInt64Writer;

import java.io.IOException;

public class DispatchInfoWriter implements ScaleWriter<DispatchInfo> {
    @Override
    public void write(ScaleCodecWriter wrt, DispatchInfo value) throws IOException {
        wrt.write(new UInt64Writer(), value.getWeight());
        wrt.writeByte(value.getCls().ordinal());
        wrt.write(ScaleCodecWriter.BOOL, !value.getPaysFee());
    }
}
