package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesUnreserved;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoBalancesUnreservedReader implements ScaleReader<FusotaoBalancesUnreserved> {
	@Override
	public FusotaoBalancesUnreserved read(ScaleCodecReader rdr) {
		var result = new FusotaoBalancesUnreserved();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam1(new DotAmount(rdr.readUint128()));
		return result;
	}
}
