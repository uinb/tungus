package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrant;
import java.io.IOException;

public class FusotaoReceiptsGrantWriter implements ScaleWriter<FusotaoReceiptsGrant> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsGrant value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getDominator());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
		wrt.writeUint128(value.getMemo());
	}
}
