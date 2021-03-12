package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemCodeUpdated;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

public class FusotaoSystemCodeUpdatedReader implements ScaleReader<FusotaoSystemCodeUpdated> {
	@Override
	public FusotaoSystemCodeUpdated read(ScaleCodecReader rdr) {
		var result = new FusotaoSystemCodeUpdated();
		return result;
	}
}
