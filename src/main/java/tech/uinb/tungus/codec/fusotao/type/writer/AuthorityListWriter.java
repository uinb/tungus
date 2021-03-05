package tech.uinb.tungus.codec.fusotao.type.writer;

import tech.uinb.tungus.codec.fusotao.type.AuthorityList;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.ListWriter;

import java.io.IOException;

public class AuthorityListWriter implements ScaleWriter<AuthorityList> {
    @Override
    public void write(ScaleCodecWriter wrt, AuthorityList value) throws IOException {
        new ListWriter(new AuthorityWriter()).write(wrt, value.getAuthorities());
    }
}
