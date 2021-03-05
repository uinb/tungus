package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeConfirmed;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class FusotaoReceiptsAssetsRevokeConfirmedReader implements ScaleReader<FusotaoReceiptsAssetsRevokeConfirmed> {
	@Override
	public FusotaoReceiptsAssetsRevokeConfirmed read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsAssetsRevokeConfirmed();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam1(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		return result;
	}
}
