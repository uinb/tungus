package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicFailed;
import tech.uinb.tungus.codec.fusotao.type.writer.DispatchErrorWriter;
import tech.uinb.tungus.codec.fusotao.type.writer.DispatchInfoWriter;

import java.io.IOException;

public class FusotaoSystemExtrinsicFailedWriter implements ScaleWriter<FusotaoSystemExtrinsicFailed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSystemExtrinsicFailed value) throws IOException {
		new DispatchErrorWriter().write(wrt, value.getParam0());
		new DispatchInfoWriter().write(wrt, value.getParam1());
	}
}
