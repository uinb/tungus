package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.StaticLookupSourceReader;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenTransfer;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoTokenTransferReader implements ScaleReader<FusotaoTokenTransfer> {
	@Override
	public FusotaoTokenTransfer read(ScaleCodecReader rdr) {
		var result = new FusotaoTokenTransfer();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setTarget(new StaticLookupSourceReader(SS58Type.Network.SUBSTRATE).read(rdr));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		return result;
	}
}
