package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesBalanceSet;
import java.io.IOException;

public class FusotaoBalancesBalanceSetWriter implements ScaleWriter<FusotaoBalancesBalanceSet> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesBalanceSet value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint128(value.getParam1().getValue());
		wrt.writeUint128(value.getParam2().getValue());
	}
}
