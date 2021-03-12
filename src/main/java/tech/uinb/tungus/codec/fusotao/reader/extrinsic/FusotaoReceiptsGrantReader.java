package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrant;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsGrantReader implements ScaleReader<FusotaoReceiptsGrant> {
	@Override
	public FusotaoReceiptsGrant read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsGrant();
		result.setDominator(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		result.setMemo(rdr.readUint128());
		return result;
	}
}
