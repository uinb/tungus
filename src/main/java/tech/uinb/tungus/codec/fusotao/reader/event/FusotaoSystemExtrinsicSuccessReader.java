package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicSuccess;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import tech.uinb.tungus.codec.fusotao.type.reader.DispatchInfoReader;

public class FusotaoSystemExtrinsicSuccessReader implements ScaleReader<FusotaoSystemExtrinsicSuccess> {
	@Override
	public FusotaoSystemExtrinsicSuccess read(ScaleCodecReader rdr) {
		var result = new FusotaoSystemExtrinsicSuccess();
		result.setParam0(new DispatchInfoReader().read(rdr));
		return result;
	}
}
