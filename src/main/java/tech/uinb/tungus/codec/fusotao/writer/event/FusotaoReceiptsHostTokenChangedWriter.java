package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsHostTokenChanged;
import java.io.IOException;

public class FusotaoReceiptsHostTokenChangedWriter implements ScaleWriter<FusotaoReceiptsHostTokenChanged> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsHostTokenChanged value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint256(value.getParam2().getPubkey());
		wrt.writeUint128(value.getParam3().getValue());
	}
}
