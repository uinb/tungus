package tech.uinb.tungus.codec.fusotao.type.writer;

import tech.uinb.tungus.codec.fusotao.type.DispatchError;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;

import java.io.IOException;

public class DispatchErrorWriter implements ScaleWriter<DispatchError> {
    @Override
    public void write(ScaleCodecWriter wrt, DispatchError value) throws IOException {
        var idx = value.getIdx();
        wrt.writeByte(idx);
        if (idx == 0) {
            wrt.writeByte(value.getModuleIdx());
            wrt.writeByte(value.getErrorIdx());
        }
    }
}
