package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoBalancesReserveRepatriated;
import java.io.IOException;

public class FusotaoBalancesReserveRepatriatedWriter implements ScaleWriter<FusotaoBalancesReserveRepatriated> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesReserveRepatriated value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint128(value.getParam2().getValue());
		wrt.writeByte(value.getParam3().ordinal());
	}
}
