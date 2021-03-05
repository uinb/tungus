package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaPaused;
import java.io.IOException;

public class FusotaoGrandpaPausedWriter implements ScaleWriter<FusotaoGrandpaPaused> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoGrandpaPaused value) throws IOException {
	}
}
