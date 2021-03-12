package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrantToken;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsGrantTokenReader implements ScaleReader<FusotaoReceiptsGrantToken> {
	@Override
	public FusotaoReceiptsGrantToken read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsGrantToken();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setDominator(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		result.setMemo(rdr.readUint128());
		return result;
	}
}
