package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTokenIssue;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.ListReader;
import io.emeraldpay.polkaj.types.DotAmount;

public class FusotaoTokenIssueReader implements ScaleReader<FusotaoTokenIssue> {
	@Override
	public FusotaoTokenIssue read(ScaleCodecReader rdr) {
		var result = new FusotaoTokenIssue();
		result.setTotal(new DotAmount(rdr.read(ScaleCodecReader.COMPACT_BIGINT)));
		result.setSymbol(new ListReader<>(ScaleCodecReader.UBYTE).read(rdr));
		return result;
	}
}
