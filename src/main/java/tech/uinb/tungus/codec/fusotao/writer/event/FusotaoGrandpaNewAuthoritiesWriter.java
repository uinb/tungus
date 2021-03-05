package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaNewAuthorities;
import java.io.IOException;
import tech.uinb.tungus.codec.fusotao.type.writer.AuthorityListWriter;

public class FusotaoGrandpaNewAuthoritiesWriter implements ScaleWriter<FusotaoGrandpaNewAuthorities> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoGrandpaNewAuthorities value) throws IOException {
		wrt.write(new AuthorityListWriter(), value.getParam0());
	}
}
