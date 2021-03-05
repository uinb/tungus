package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoReceiptsMergeToAddStashToken;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import io.emeraldpay.polkaj.types.Hash256;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoReceiptsMergeToAddStashTokenReader implements ScaleReader<FusotaoReceiptsMergeToAddStashToken> {
	@Override
	public FusotaoReceiptsMergeToAddStashToken read(ScaleCodecReader rdr) {
		var result = new FusotaoReceiptsMergeToAddStashToken();
		result.setToken(new Hash256(rdr.readUint256()));
		result.setFundOwner(new Hash256(rdr.readUint256()));
		result.setAmount(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		result.setDigest(new ListReader<>(ScaleCodecReader.UBYTE).read(rdr));
		return result;
	}
}
