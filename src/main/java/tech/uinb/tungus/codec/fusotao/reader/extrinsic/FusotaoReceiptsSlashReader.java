package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsSlash;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;

public class FusotaoReceiptsSlashReader implements ScaleReader<FusotaoReceiptsSlash> {
	@Override
	public FusotaoReceiptsSlash read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsSlash();
		result.setDominator(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		return result;
	}
}
