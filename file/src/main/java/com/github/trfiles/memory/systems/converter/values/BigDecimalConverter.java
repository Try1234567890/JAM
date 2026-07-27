package com.github.trfiles.memory.systems.converter.values;

import java.math.BigDecimal;
import java.util.Optional;

public class BigDecimalConverter implements Converter<BigDecimal> {
    private BigDecimalConverter() {
    }

    private record Holder() {
        private static final BigDecimalConverter INSTANCE = new BigDecimalConverter();
    }

    public static BigDecimalConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<BigDecimal> convert(Object object) {
        if (object instanceof BigDecimal bigDecimal) return Optional.of(bigDecimal);

        if (object instanceof String str) {
            try {
                return Optional.of(new BigDecimal(str));
            } catch (NumberFormatException _) {
                return Optional.empty();
            }
        }

        if (object instanceof Number number) {
            return Optional.of(BigDecimal.valueOf(number.longValue()));
        }

        return Optional.empty();
    }
}