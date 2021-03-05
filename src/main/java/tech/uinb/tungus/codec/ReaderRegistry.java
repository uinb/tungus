package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleReader;

public interface ReaderRegistry {
    ScaleReader getReader(String name);
}
