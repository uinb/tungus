package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenRepatriated;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.Address;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoTokenTokenRepatriatedReader implements ScaleReader<FusotaoTokenTokenRepatriated> {
	@Override
	public FusotaoTokenTokenRepatriated read(ScaleCodecReader rdr) {
		var result = new FusotaoTokenTokenRepatriated();
		result.setParam0(new Hash256(rdr.readUint256()));
		result.setParam1(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam2(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		result.setParam3(new DotAmount(rdr.readUint128()));
		return result;
	}
}
