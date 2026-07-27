package com.github.trfiles.memory.systems.converter.values;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public class CollectionConverter<T, C extends Collection<T>> implements Converter<C> {
    private final Converter<T> elementConverter;
    private final Supplier<C> newInstance;

    protected CollectionConverter(Converter<T> elementConverter,
                                  Supplier<C> newInstance) {
        this.elementConverter = elementConverter;
        this.newInstance = newInstance;
    }

    @Override
    public Optional<C> convert(Object object) {
        if (object instanceof Collection<?> coll) {
            C newColl = newInstance.get();

            for (Object element : coll) {
                Optional<T> newElement = elementConverter.convert(element);
                newElement.ifPresent(newColl::add);
            }

            return Optional.ofNullable(newColl);
        }
        return Optional.empty();
    }
}