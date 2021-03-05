package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class StaticLookupSourceReader implements ScaleReader<Address> {
    private final SS58Type.Network network;

    public StaticLookupSourceReader(SS58Type.Network network) {
        this.network = network;
    }

    @Override
    public Address read(ScaleCodecReader rdr) {
        rdr.skip(1);
        return new Address(network, rdr.readUint256());
    }
}
