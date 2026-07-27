package com.github.trfiles.memory.systems.converter.values;

import java.util.Optional;
import java.util.function.Function;

public interface Converter<T> extends Function<Object, T> {

    Optional<T> convert(Object object);


    @Override
    default T apply(Object object) {
        return convert(object).orElse(null);
    }
}
