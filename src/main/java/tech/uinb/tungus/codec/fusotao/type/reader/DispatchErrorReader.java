package tech.uinb.tungus.codec.fusotao.type.reader;

import tech.uinb.tungus.codec.fusotao.type.DispatchError;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class DispatchErrorReader implements ScaleReader<DispatchError> {
    @Override
    public DispatchError read(ScaleCodecReader rdr) {
        var result = new DispatchError();
        var idx = rdr.readUByte();
        result.setIdx(idx);
        if (idx == 0) {
            result.setModuleIdx(rdr.readUByte());
            result.setErrorIdx(rdr.readUByte());
        }
        return result;
    }
}
