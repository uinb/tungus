package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenRevokeSubmitted;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;

public class FusotaoReceiptsTokenRevokeSubmittedReader implements ScaleReader<FusotaoReceiptsTokenRevokeSubmitted> {
	@Override
	public FusotaoReceiptsTokenRevokeSubmitted read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsTokenRevokeSubmitted();
		result.setParam0(new Hash256(rdr.readUint256()));
		result.setParam1(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam2(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		return result;
	}
}
