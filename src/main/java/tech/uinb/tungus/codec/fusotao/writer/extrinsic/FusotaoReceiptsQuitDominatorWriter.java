package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsQuitDominator;
import java.io.IOException;
import io.emeraldpay.polkaj.types.Hash256;

public class FusotaoReceiptsQuitDominatorWriter implements ScaleWriter<FusotaoReceiptsQuitDominator> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsQuitDominator value) throws IOException {
		wrt.writeOptional((ScaleWriter<Hash256>) (w, v) -> w.writeUint256(v.getBytes()), value.getToken());
	}
}
