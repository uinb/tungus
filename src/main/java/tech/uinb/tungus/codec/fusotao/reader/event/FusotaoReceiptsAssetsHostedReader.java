package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsHosted;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsAssetsHostedReader implements ScaleReader<FusotaoReceiptsAssetsHosted> {
	@Override
	public FusotaoReceiptsAssetsHosted read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsAssetsHosted();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam1(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam2(new DotAmount(rdr.readUint128()));
		return result;
	}
}
