package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenRevokeConfirmed;
import java.io.IOException;

public class FusotaoReceiptsTokenRevokeConfirmedWriter implements ScaleWriter<FusotaoReceiptsTokenRevokeConfirmed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsTokenRevokeConfirmed value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint256(value.getParam2().getPubkey());
	}
}
