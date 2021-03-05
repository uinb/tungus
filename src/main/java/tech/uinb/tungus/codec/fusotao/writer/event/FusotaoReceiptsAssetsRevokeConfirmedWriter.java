package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsAssetsRevokeConfirmed;
import java.io.IOException;

public class FusotaoReceiptsAssetsRevokeConfirmedWriter implements ScaleWriter<FusotaoReceiptsAssetsRevokeConfirmed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsAssetsRevokeConfirmed value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint256(value.getParam1().getPubkey());
	}
}
