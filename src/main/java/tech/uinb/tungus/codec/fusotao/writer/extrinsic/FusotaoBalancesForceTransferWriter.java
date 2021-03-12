package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesForceTransfer;
import java.io.IOException;

public class FusotaoBalancesForceTransferWriter implements ScaleWriter<FusotaoBalancesForceTransfer> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesForceTransfer value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getSource());
		new StaticLookupSourceWriter().write(wrt, value.getDest());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getValue().getValue());
	}
}
