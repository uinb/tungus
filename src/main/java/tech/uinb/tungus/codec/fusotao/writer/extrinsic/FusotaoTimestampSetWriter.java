package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTimestampSet;
import java.io.IOException;

public class FusotaoTimestampSetWriter implements ScaleWriter<FusotaoTimestampSet> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoTimestampSet value) throws IOException {
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getNow());
	}
}
