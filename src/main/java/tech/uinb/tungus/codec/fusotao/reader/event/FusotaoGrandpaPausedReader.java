package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaPaused;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class FusotaoGrandpaPausedReader implements ScaleReader<FusotaoGrandpaPaused> {
	@Override
	public FusotaoGrandpaPaused read(ScaleCodecReader rdr) {
		var result = new FusotaoGrandpaPaused();
		return result;
	}
}
