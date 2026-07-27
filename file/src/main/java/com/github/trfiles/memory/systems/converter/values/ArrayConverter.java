package com.github.trfiles.memory.systems.converter.values;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

public class ArrayConverter<T> implements Converter<T[]> {
    private final Converter<T> elementConverter;
    private final IntFunction<T[]> newInstance;

    protected ArrayConverter(Converter<T> elementConverter,
                             IntFunction<T[]> newInstance) {
        this.elementConverter = elementConverter;
        this.newInstance = newInstance;
    }

    @Override
    public Optional<T[]> convert(Object object) {
        if (object == null || !object.getClass().isArray()) return Optional.empty();
        int len = Array.getLength(object);
        List<T> result = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            Object element = Array.get(object, i);
            Optional<T> newElement = elementConverter.convert(element);
            newElement.ifPresent(result::add);
        }

        return Optional.ofNullable(result.toArray(newInstance));
    }
}