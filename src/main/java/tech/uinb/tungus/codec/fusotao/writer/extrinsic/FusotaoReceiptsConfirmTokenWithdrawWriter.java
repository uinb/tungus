package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmTokenWithdraw;
import java.io.IOException;

public class FusotaoReceiptsConfirmTokenWithdrawWriter implements ScaleWriter<FusotaoReceiptsConfirmTokenWithdraw> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsConfirmTokenWithdraw value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		wrt.writeUint256(value.getFundOwner().getBytes());
	}
}
