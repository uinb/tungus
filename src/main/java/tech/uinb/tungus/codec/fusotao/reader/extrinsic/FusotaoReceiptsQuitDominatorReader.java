package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsQuitDominator;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.types.Hash256;

public class FusotaoReceiptsQuitDominatorReader implements ScaleReader<FusotaoReceiptsQuitDominator> {
	@Override
	public FusotaoReceiptsQuitDominator read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsQuitDominator();
		result.setToken(rdr.readOptional(r-> new Hash256(rdr.readUint256())));
		return result;
	}
}
