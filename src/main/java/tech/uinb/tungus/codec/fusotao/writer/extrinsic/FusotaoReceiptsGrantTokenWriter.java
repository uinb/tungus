package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsGrantToken;
import java.io.IOException;

public class FusotaoReceiptsGrantTokenWriter implements ScaleWriter<FusotaoReceiptsGrantToken> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsGrantToken value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		new StaticLookupSourceWriter().write(wrt, value.getDominator());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
		wrt.writeUint128(value.getMemo());
	}
}
