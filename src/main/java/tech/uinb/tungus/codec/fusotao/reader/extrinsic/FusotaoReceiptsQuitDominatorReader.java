package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsQuitDominator;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;

public class FusotaoReceiptsQuitDominatorReader implements ScaleReader<FusotaoReceiptsQuitDominator> {
	@Override
	public FusotaoReceiptsQuitDominator read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsQuitDominator();
		return result;
	}
}
