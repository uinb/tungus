package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimTokenDominator;
import java.io.IOException;

public class FusotaoReceiptsClaimTokenDominatorWriter implements ScaleWriter<FusotaoReceiptsClaimTokenDominator> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsClaimTokenDominator value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
	}
}
