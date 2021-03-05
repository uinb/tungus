package tech.uinb.tungus.codec.fusotao.type.reader;

import tech.uinb.tungus.codec.fusotao.type.Authority;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UInt64Reader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class AuthorityReader implements ScaleReader<Authority> {
    @Override
    public Authority read(ScaleCodecReader rdr) {
        var result = new Authority();
        result.setAuthorityId(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
        result.setAuthorityWeight(rdr.read(new UInt64Reader()));
        return result;
    }
}
