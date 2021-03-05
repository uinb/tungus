package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoGrandpaResumed;
import java.io.IOException;

public class FusotaoGrandpaResumedWriter implements ScaleWriter<FusotaoGrandpaResumed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoGrandpaResumed value) throws IOException {
	}
}
