package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSessionNewSession;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

public class FusotaoSessionNewSessionReader implements ScaleReader<FusotaoSessionNewSession> {
	@Override
	public FusotaoSessionNewSession read(ScaleCodecReader rdr) {
		var result = new FusotaoSessionNewSession();
		result.setParam0(rdr.readUint32());
		return result;
	}
}
