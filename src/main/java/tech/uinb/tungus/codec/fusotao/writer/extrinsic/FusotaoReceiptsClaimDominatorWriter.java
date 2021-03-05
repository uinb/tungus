package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsClaimDominator;
import java.io.IOException;

public class FusotaoReceiptsClaimDominatorWriter implements ScaleWriter<FusotaoReceiptsClaimDominator> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsClaimDominator value) throws IOException {
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
	}
}
