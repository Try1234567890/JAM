package com.github.trfiles.memory.systems;

import com.github.trfiles.memory.systems.converter.values.Converter;
import com.github.trfiles.memory.systems.converter.values.Converters;
import com.github.utilities.validators.Preconditions;

public record ValueKey<T>(String path, Converter<T> converter, T def) {
    public ValueKey {
        Preconditions.parameterNotNull(path, "path", "The path cannot be null or empty");
        Preconditions.simpleParameterNotNull(converter, "converter");
    }

    public ValueKey(String path,
                    Converter<T> converter) {
        this(path, converter, null);
    }

    public ValueKey(String path,
                    Class<T> type) {
        this(path, Converters.typeConverter(type), null);
    }
}
