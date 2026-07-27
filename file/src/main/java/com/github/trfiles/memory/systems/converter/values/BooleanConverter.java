package com.github.trfiles.memory.systems.converter.values;

import java.util.Optional;

public class BooleanConverter implements Converter<Boolean> {
    private BooleanConverter() {
    }

    private record Holder() {
        private static final BooleanConverter INSTANCE = new BooleanConverter();
    }

    public static BooleanConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Boolean> convert(Object object) {
        if (object instanceof Boolean bool) return Optional.of(bool);

        if (object instanceof String str) {
            if (isBoolean(str))
                return Optional.of(Boolean.parseBoolean(str));
        }

        if (object instanceof Number number) {
            int intValue = number.intValue();
            if (intValue == 1) return Optional.of(Boolean.TRUE);
            if (intValue == 0) return Optional.of(Boolean.FALSE);
        }

        return Optional.empty();
    }

    private static boolean isBoolean(String str) {
        return str.equalsIgnoreCase("true")
                || str.equalsIgnoreCase("false");
    }
}