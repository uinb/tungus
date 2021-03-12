package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaPaused;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

public class FusotaoGrandpaPausedReader implements ScaleReader<FusotaoGrandpaPaused> {
	@Override
	public FusotaoGrandpaPaused read(ScaleCodecReader rdr) {
		var result = new FusotaoGrandpaPaused();
		return result;
	}
}
