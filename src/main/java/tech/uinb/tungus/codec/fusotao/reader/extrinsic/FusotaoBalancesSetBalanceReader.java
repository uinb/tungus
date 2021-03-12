package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesSetBalance;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoBalancesSetBalanceReader implements ScaleReader<FusotaoBalancesSetBalance> {
	@Override
	public FusotaoBalancesSetBalance read(ScaleCodecReader rdr) {
		var result = new FusotaoBalancesSetBalance();
		result.setWho(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		result.setNewFree(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		result.setNewReserved(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		return result;
	}
}
