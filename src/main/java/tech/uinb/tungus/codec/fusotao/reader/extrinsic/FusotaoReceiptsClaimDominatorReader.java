package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimDominator;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsClaimDominatorReader implements ScaleReader<FusotaoReceiptsClaimDominator> {
	@Override
	public FusotaoReceiptsClaimDominator read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsClaimDominator();
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		return result;
	}
}
