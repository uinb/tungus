package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimTokenDominator;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsClaimTokenDominatorReader implements ScaleReader<FusotaoReceiptsClaimTokenDominator> {
	@Override
	public FusotaoReceiptsClaimTokenDominator read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsClaimTokenDominator();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		return result;
	}
}
