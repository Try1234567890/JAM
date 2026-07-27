package com.github.trfiles.memory.systems.converter.values;

import com.github.trfiles.Section;
import com.github.trfiles.memory.MemoryConfiguration;

import java.util.Map;
import java.util.Optional;

public class SectionConverter implements Converter<Section> {
    private SectionConverter() {
    }

    private record Holder() {
        private static final SectionConverter INSTANCE = new SectionConverter();
    }

    public static SectionConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Section> convert(Object object) {
        if (object instanceof Section sec) {
            return Optional.of(sec);
        }
        if (object instanceof Map<?, ?> map) {
            return Optional.of(new MemoryConfiguration(map));
        }
        return Optional.empty();
    }
}