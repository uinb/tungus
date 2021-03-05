package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenBurned;
import java.io.IOException;

public class FusotaoTokenTokenBurnedWriter implements ScaleWriter<FusotaoTokenTokenBurned> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoTokenTokenBurned value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint128(value.getParam2().getValue());
	}
}
