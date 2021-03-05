package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenRevokeSubmitted;
import java.io.IOException;

public class FusotaoReceiptsTokenRevokeSubmittedWriter implements ScaleWriter<FusotaoReceiptsTokenRevokeSubmitted> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsTokenRevokeSubmitted value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint256(value.getParam2().getPubkey());
	}
}
