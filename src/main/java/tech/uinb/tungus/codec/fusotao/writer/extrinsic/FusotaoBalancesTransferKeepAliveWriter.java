package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoBalancesTransferKeepAlive;
import java.io.IOException;

public class FusotaoBalancesTransferKeepAliveWriter implements ScaleWriter<FusotaoBalancesTransferKeepAlive> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoBalancesTransferKeepAlive value) throws IOException {
		new StaticLookupSourceWriter().write(wrt, value.getDest());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getValue().getValue());
	}
}
