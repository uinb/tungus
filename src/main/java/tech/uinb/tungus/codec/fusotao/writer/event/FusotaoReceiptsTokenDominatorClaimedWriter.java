package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsTokenDominatorClaimed;
import java.io.IOException;

public class FusotaoReceiptsTokenDominatorClaimedWriter implements ScaleWriter<FusotaoReceiptsTokenDominatorClaimed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsTokenDominatorClaimed value) throws IOException {
		wrt.writeUint256(value.getParam0().getBytes());
		wrt.writeUint256(value.getParam1().getPubkey());
		wrt.writeUint128(value.getParam2().getValue());
	}
}
