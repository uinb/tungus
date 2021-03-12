package tech.uinb.tungus.codec.fusotao.reader.extrinsic;

import tech.uinb.tungus.codec.fusotao.bean.extrinsic.FusotaoTimestampSet;
import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scaletypes.Metadata;
import java.math.BigInteger;

public class FusotaoTimestampSetReader implements ScaleReader<FusotaoTimestampSet> {
	@Override
	public FusotaoTimestampSet read(ScaleCodecReader rdr) {
		var result = new FusotaoTimestampSet();
		result.setNow(rdr.read(ScaleCodecReader.COMPACT_BIGINT));
		return result;
	}
}
