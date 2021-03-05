package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeSubmitted;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class FusotaoReceiptsAssetsRevokeSubmittedReader implements ScaleReader<FusotaoReceiptsAssetsRevokeSubmitted> {
	@Override
	public FusotaoReceiptsAssetsRevokeSubmitted read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsAssetsRevokeSubmitted();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam1(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		return result;
	}
}
