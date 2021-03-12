package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevoke;
import java.io.IOException;

public class FusotaoReceiptsRevokeWriter implements ScaleWriter<FusotaoReceiptsRevoke> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsRevoke value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getDominator());
	}
}
