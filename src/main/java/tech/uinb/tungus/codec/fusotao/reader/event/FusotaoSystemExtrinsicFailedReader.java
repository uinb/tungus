package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicFailed;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.fusotao.type.reader.DispatchInfoReader;
import tech.uinb.tungus.codec.fusotao.type.reader.DispatchErrorReader;

public class FusotaoSystemExtrinsicFailedReader implements ScaleReader<FusotaoSystemExtrinsicFailed> {
	@Override
	public FusotaoSystemExtrinsicFailed read(ScaleCodecReader rdr) {
		var result = new FusotaoSystemExtrinsicFailed();
		result.setParam0(new DispatchErrorReader().read(rdr));
		result.setParam1(new DispatchInfoReader().read(rdr));
		return result;
	}
}
