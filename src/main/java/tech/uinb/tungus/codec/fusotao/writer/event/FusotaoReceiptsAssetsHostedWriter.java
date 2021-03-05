package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsHosted;
import java.io.IOException;

public class FusotaoReceiptsAssetsHostedWriter implements ScaleWriter<FusotaoReceiptsAssetsHosted> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsAssetsHosted value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint128(value.getParam2().getValue());
	}
}
