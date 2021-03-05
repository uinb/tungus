package tech.uinb.tungus.codec.fusotao.writer.event;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.event.FusotaoReceiptsDominatorClaimed;
import java.io.IOException;

public class FusotaoReceiptsDominatorClaimedWriter implements ScaleWriter<FusotaoReceiptsDominatorClaimed> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsDominatorClaimed value) throws IOException {
		wrt.writeUint256(value.getParam0().getPubkey());
		wrt.writeUint128(value.getParam1().getValue());
	}
}
