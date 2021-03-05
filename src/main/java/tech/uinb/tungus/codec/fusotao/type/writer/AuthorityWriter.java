package tech.uinb.tungus.codec.fusotao.type.writer;

import tech.uinb.tungus.codec.fusotao.type.Authority;
import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UInt64Writer;

import java.io.IOException;

public class AuthorityWriter implements ScaleWriter<Authority> {
    @Override
    public void write(ScaleCodecWriter wrt, Authority value) throws IOException {
        wrt.writeUint256(value.getAuthorityId().getPubkey());
        wrt.write(new UInt64Writer(), value.getAuthorityWeight());
    }
}
