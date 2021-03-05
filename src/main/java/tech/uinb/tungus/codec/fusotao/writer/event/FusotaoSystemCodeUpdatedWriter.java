package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemCodeUpdated;
import java.io.IOException;

public class FusotaoSystemCodeUpdatedWriter implements ScaleWriter<FusotaoSystemCodeUpdated> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSystemCodeUpdated value) throws IOException {
	}
}
