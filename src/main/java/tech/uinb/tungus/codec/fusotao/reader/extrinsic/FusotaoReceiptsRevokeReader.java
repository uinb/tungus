package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevoke;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import tech.uinb.tungus.codec.StaticLookupSourceReader;

public class FusotaoReceiptsRevokeReader implements ScaleReader<FusotaoReceiptsRevoke> {
	@Override
	public FusotaoReceiptsRevoke read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsRevoke();
		result.setDominator(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		return result;
	}
}
