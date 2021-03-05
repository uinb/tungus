package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemKilledAccount;
import java.io.IOException;

public class FusotaoSystemKilledAccountWriter implements ScaleWriter<FusotaoSystemKilledAccount> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSystemKilledAccount value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
	}
}
