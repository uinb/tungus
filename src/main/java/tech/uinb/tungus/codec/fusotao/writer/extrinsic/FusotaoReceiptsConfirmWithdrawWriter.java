package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsConfirmWithdraw;
import java.io.IOException;

public class FusotaoReceiptsConfirmWithdrawWriter implements ScaleWriter<FusotaoReceiptsConfirmWithdraw> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsConfirmWithdraw value) throws IOException {
		wrt.writeUint256(value.getFundOwner().getBytes());
	}
}
