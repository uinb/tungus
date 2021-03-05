package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenTransfer;
import java.io.IOException;

import tech.uinb.tungus.codec.StaticLookupSourceWriter;

public class FusotaoTokenTransferWriter implements ScaleWriter<FusotaoTokenTransfer> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoTokenTransfer value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		new StaticLookupSourceWriter().write(wrt, value.getTarget());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
	}
}
