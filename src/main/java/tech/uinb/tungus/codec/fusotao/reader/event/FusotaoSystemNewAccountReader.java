package tech.uinb.tungus.codec.fusotao.reader.event;

import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemNewAccount;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.ss58.SS58Type;
import io.emeraldpay.polkaj.types.Address;

public class FusotaoSystemNewAccountReader implements ScaleReader<FusotaoSystemNewAccount> {
	@Override
	public FusotaoSystemNewAccount read(ScaleCodecReader rdr) {
		var result = new FusotaoSystemNewAccount();
		result.setParam0(new Address(SS58Type.Network.SUBSTRATE, rdr.readUint256()));
		return result;
	}
}
