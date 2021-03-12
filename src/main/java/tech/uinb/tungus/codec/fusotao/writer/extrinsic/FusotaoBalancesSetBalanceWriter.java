package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesSetBalance;
import java.io.IOException;

public class FusotaoBalancesSetBalanceWriter implements ScaleWriter<FusotaoBalancesSetBalance> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesSetBalance value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getWho());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getNewFree().getValue());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getNewReserved().getValue());
	}
}
