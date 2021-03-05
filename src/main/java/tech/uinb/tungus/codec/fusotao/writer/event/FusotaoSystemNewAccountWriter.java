package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemNewAccount;
import java.io.IOException;

public class FusotaoSystemNewAccountWriter implements ScaleWriter<FusotaoSystemNewAccount> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSystemNewAccount value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
	}
}
