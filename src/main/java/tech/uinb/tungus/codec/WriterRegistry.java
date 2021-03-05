package tech.uinb.tungus.codec;

import io.emeraldpay.polkaj.scale.ScaleWriter;

public interface WriterRegistry {
    ScaleWriter getWriter(Class clazz);
}
