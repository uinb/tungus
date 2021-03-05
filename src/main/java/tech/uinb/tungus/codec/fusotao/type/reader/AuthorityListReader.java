package tech.uinb.tungus.codec.fusotao.type.reader;

import tech.uinb.tungus.codec.fusotao.type.AuthorityList;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;

public class AuthorityListReader implements ScaleReader<AuthorityList> {
    @Override
    public AuthorityList read(ScaleCodecReader rdr) {
        var list = new ListReader(new AuthorityReader()).read(rdr);
        AuthorityList result = new AuthorityList();
        result.setAuthorities(list);
        return result;
    }
}
