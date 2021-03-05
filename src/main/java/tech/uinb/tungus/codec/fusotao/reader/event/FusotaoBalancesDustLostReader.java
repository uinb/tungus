package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesDustLost;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoBalancesDustLostReader implements ScaleReader<FusotaoBalancesDustLost> {
	@Override
	public FusotaoBalancesDustLost read(ScaleCodecReader rdr) {
		var result = new FusotaoBalancesDustLost();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam1(new DotAmount(rdr.readUint128()));
		return result;
	}
}
