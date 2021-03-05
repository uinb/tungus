package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsSlash;
import java.io.IOException;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;

public class FusotaoReceiptsSlashWriter implements ScaleWriter<FusotaoReceiptsSlash> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsSlash value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getDominator());
	}
}
