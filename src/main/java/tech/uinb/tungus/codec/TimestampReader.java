package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;

import java.math.BigInteger;

public class TimestampReader implements ScaleReader<BigInteger> {
    @Override
    public BigInteger read(ScaleCodecReader rdr) {
        rdr.skip(4);
        return ScaleCodecReader.COMPACT_BIGINT.read(rdr);
    }
}
