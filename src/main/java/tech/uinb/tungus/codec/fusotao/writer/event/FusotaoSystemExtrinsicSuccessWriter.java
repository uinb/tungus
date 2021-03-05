package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoSystemExtrinsicSuccess;
import java.io.IOException;
import tech.uinb.tungus.codec.fusotao.type.writer.DispatchInfoWriter;

public class FusotaoSystemExtrinsicSuccessWriter implements ScaleWriter<FusotaoSystemExtrinsicSuccess> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoSystemExtrinsicSuccess value) throws IOException {
		new DispatchInfoWriter().write(wrt, value.getParam0());
	}
}
