package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaResumed;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

public class FusotaoGrandpaResumedReader implements ScaleReader<FusotaoGrandpaResumed> {
	@Override
	public FusotaoGrandpaResumed read(ScaleCodecReader rdr) {
		var result = new FusotaoGrandpaResumed();
		return result;
	}
}
