package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaNewAuthorities;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.fusotao.type.reader.AuthorityListReader;

public class FusotaoGrandpaNewAuthoritiesReader implements ScaleReader<FusotaoGrandpaNewAuthorities> {
	@Override
	public FusotaoGrandpaNewAuthorities read(ScaleCodecReader rdr) {
		var result = new FusotaoGrandpaNewAuthorities();
		result.setParam0(rdr.read(new AuthorityListReader()));
		return result;
	}
}
