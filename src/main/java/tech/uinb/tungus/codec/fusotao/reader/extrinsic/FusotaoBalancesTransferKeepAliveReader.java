package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesTransferKeepAlive;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoBalancesTransferKeepAliveReader implements ScaleReader<FusotaoBalancesTransferKeepAlive> {
	@Override
	public FusotaoBalancesTransferKeepAlive read(ScaleCodecReader rdr) {
		var result = new FusotaoBalancesTransferKeepAlive();
		result.setDest(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		result.setValue(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		return result;
	}
}
