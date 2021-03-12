package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.StaticLookupSourceWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsRevokeToken;
import java.io.IOException;

public class FusotaoReceiptsRevokeTokenWriter implements ScaleWriter<FusotaoReceiptsRevokeToken> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsRevokeToken value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		new StaticLookupSourceWriter().write(wrt, value.getDominator());
	}
}
