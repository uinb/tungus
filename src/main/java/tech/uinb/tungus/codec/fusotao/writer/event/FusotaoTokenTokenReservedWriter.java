package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoTokenTokenReserved;
import java.io.IOException;

public class FusotaoTokenTokenReservedWriter implements ScaleWriter<FusotaoTokenTokenReserved> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoTokenTokenReserved value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint128(value.getParam2().getValue());
	}
}
