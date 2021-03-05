package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesDeposit;
import java.io.IOException;

public class FusotaoBalancesDepositWriter implements ScaleWriter<FusotaoBalancesDeposit> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesDeposit value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint128(value.getParam1().getValue());
	}
}
