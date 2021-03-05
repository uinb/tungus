package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeSubmitted;
import java.io.IOException;

public class FusotaoReceiptsAssetsRevokeSubmittedWriter implements ScaleWriter<FusotaoReceiptsAssetsRevokeSubmitted> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsAssetsRevokeSubmitted value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint256(value.getParam1().getPubkey());
	}
}
