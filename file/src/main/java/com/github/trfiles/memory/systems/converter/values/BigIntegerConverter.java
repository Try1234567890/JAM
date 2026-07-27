package com.github.trfiles.memory.systems.converter.values;


import java.math.BigInteger;
import java.util.Optional;

public class BigIntegerConverter implements Converter<BigInteger> {
    private BigIntegerConverter() {
    }

    private record Holder() {
        private static final BigIntegerConverter INSTANCE = new BigIntegerConverter();
    }

    public static BigIntegerConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<BigInteger> convert(Object object) {
        if (object instanceof BigInteger bigInt) return Optional.of(bigInt);

        if (object instanceof String str) {
            try {
                return Optional.of(new BigInteger(str));
            } catch (NumberFormatException _) {
                return Optional.empty();
            }
        }

        if (object instanceof Number number) {
            return Optional.of(BigInteger.valueOf(number.longValue()));
        }

        return Optional.empty();
    }
}