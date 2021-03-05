package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSessionNewSession;
import java.io.IOException;

public class FusotaoSessionNewSessionWriter implements ScaleWriter<FusotaoSessionNewSession> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSessionNewSession value) throws IOException {
		wrt.writeUint32(value.getParam0());
	}
}
