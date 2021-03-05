package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevokeToken;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Hash256;
import tech.uinb.tungus.codec.StaticLookupSourceReader;

public class FusotaoReceiptsRevokeTokenReader implements ScaleReader<FusotaoReceiptsRevokeToken> {
	@Override
	public FusotaoReceiptsRevokeToken read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsRevokeToken();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setDominator(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		return result;
	}
}
