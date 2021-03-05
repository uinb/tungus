package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenIssue;
import java.io.IOException;

import io.emeraldpay.polkaj.scale.writer.ListWriter;

public class FusotaoTokenIssueWriter implements ScaleWriter<FusotaoTokenIssue> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoTokenIssue value) throws IOException {
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getTotal().getValue());
		new ListWriter<Integer>((w, v) -> w.writeByte(v.byteValue())).write(wrt, value.getSymbol());
	}
}
