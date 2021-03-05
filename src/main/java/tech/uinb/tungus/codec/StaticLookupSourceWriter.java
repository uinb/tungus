package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.types.Address;

import java.io.IOException;

public class StaticLookupSourceWriter implements ScaleWriter<Address> {
    @Override
    public void write(ScaleCodecWriter wrt, Address address) throws IOException {
        wrt.writeByte(0);
        wrt.writeUint256(address.getPubkey());
    }
}
