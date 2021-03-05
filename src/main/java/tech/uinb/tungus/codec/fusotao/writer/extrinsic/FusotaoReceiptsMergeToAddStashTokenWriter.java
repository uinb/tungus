package tech.uinb.tungus.codec.fusotao.writer.extrinsic;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToAddStashToken;
import java.io.IOException;
import io.emeraldpay.polkaj.scale.writer.ListWriter;

public class FusotaoReceiptsMergeToAddStashTokenWriter implements ScaleWriter<FusotaoReceiptsMergeToAddStashToken> {
	@Override
	public void write(ScaleCodecWriter wrt, FusotaoReceiptsMergeToAddStashToken value) throws IOException {
		wrt.writeUint256(value.getToken().getBytes());
		wrt.writeUint256(value.getFundOwner().getBytes());
		wrt.write(ScaleCodecWriter.COMPACT_BIGINT, value.getAmount().getValue());
		new ListWriter<Integer>((w, v) -> w.writeByte(v.byteValue())).write(wrt, value.getDigest());
	}
}
