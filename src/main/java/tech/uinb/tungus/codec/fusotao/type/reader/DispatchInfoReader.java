package tech.uinb.tungus.codec.fusotao.type.reader;

import tech.uinb.tungus.codec.fusotao.type.DispatchInfo;
import io.emeraldpay.polkaj.json.RuntimeDispatchInfoJson.DispatchClass;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UInt64Reader;

public class DispatchInfoReader implements ScaleReader<DispatchInfo> {
    @Override
    public DispatchInfo read(ScaleCodecReader rdr) {
        var result = new DispatchInfo();
        result.setWeight(rdr.read(new UInt64Reader()));
        result.setCls(DispatchClass.values()[rdr.readUByte()]);
        result.setPaysFee(!rdr.readBoolean());
        return result;
    }
}
