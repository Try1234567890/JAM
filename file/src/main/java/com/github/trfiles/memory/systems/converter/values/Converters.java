package com.github.trfiles.memory.systems.converter.values;

import com.github.trfiles.Section;
import com.github.utilities.registries.Registry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Converters extends Registry<Class<?>, Converter<?>> {
    public static final Converter<Number> NUMBER_CONVERTER = typeConverter(Number.class);
    public static final Converter<Section> SECTION_CONVERTER = SectionConverter.getInstance();
    public static final Converter<Section[]> SECTION_ARRAY_CONVERTER = new ArrayConverter<>(SECTION_CONVERTER, Section[]::new);
    public static final Converter<List<Section>> SECTION_LIST_CONVERTER = new CollectionConverter<>(SECTION_CONVERTER, ArrayList::new);
    public static final Converter<Set<Section>> SECTION_SET_CONVERTER = new CollectionConverter<>(SECTION_CONVERTER, HashSet::new);

    public static final Converter<String> STRING_CONVERTER = typeConverter(String.class);
    public static final Converter<String[]> STRING_ARRAY_CONVERTER = new ArrayConverter<>(STRING_CONVERTER, String[]::new);
    public static final Converter<List<String>> STRING_LIST_CONVERTER = new CollectionConverter<>(STRING_CONVERTER, ArrayList::new);
    public static final Converter<Set<String>> STRING_SET_CONVERTER = new CollectionConverter<>(STRING_CONVERTER, HashSet::new);

    public static final Converter<Object> OBJECT_CONVERTER = Optional::ofNullable;
    public static final Converter<Object[]> OBJECT_ARRAY_CONVERTER = new ArrayConverter<>(OBJECT_CONVERTER, Object[]::new);
    public static final Converter<List<Object>> OBJECT_LIST_CONVERTER = new CollectionConverter<>(OBJECT_CONVERTER, ArrayList::new);
    public static final Converter<Set<Object>> OBJECT_SET_CONVERTER = new CollectionConverter<>(OBJECT_CONVERTER, HashSet::new);

    public static final Converter<BigDecimal> BIG_DECIMAL_CONVERTER = BigDecimalConverter.getInstance();
    public static final Converter<BigDecimal[]> BIG_DECIMAL_ARRAY_CONVERTER = new ArrayConverter<>(BIG_DECIMAL_CONVERTER, BigDecimal[]::new);
    public static final Converter<List<BigDecimal>> BIG_DECIMAL_LIST_CONVERTER = new CollectionConverter<>(BIG_DECIMAL_CONVERTER, ArrayList::new);
    public static final Converter<Set<BigDecimal>> BIG_DECIMAL_SET_CONVERTER = new CollectionConverter<>(BIG_DECIMAL_CONVERTER, HashSet::new);

    public static final Converter<BigInteger> BIG_INTEGER_CONVERTER = BigIntegerConverter.getInstance();
    public static final Converter<BigInteger[]> BIG_INTEGER_ARRAY_CONVERTER = new ArrayConverter<>(BIG_INTEGER_CONVERTER, BigInteger[]::new);
    public static final Converter<List<BigInteger>> BIG_INTEGER_LIST_CONVERTER = new CollectionConverter<>(BIG_INTEGER_CONVERTER, ArrayList::new);
    public static final Converter<Set<BigInteger>> BIG_INTEGER_SET_CONVERTER = new CollectionConverter<>(BIG_INTEGER_CONVERTER, HashSet::new);

    public static final Converter<Boolean> BOOLEAN_CONVERTER = BooleanConverter.getInstance();
    public static final Converter<Boolean[]> BOOLEAN_ARRAY_CONVERTER = new ArrayConverter<>(BOOLEAN_CONVERTER, Boolean[]::new);
    public static final Converter<List<Boolean>> BOOLEAN_LIST_CONVERTER = new CollectionConverter<>(BOOLEAN_CONVERTER, ArrayList::new);
    public static final Converter<Set<Boolean>> BOOLEAN_SET_CONVERTER = new CollectionConverter<>(BOOLEAN_CONVERTER, HashSet::new);

    public static final Converter<Byte> BYTE_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::byteValue);
    public static final Converter<Byte[]> BYTE_ARRAY_CONVERTER = new ArrayConverter<>(BYTE_CONVERTER, Byte[]::new);
    public static final Converter<List<Byte>> BYTE_LIST_CONVERTER = new CollectionConverter<>(BYTE_CONVERTER, ArrayList::new);
    public static final Converter<Set<Byte>> BYTE_SET_CONVERTER = new CollectionConverter<>(BYTE_CONVERTER, HashSet::new);

    public static final Converter<Short> SHORT_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::shortValue);
    public static final Converter<Short[]> SHORT_ARRAY_CONVERTER = new ArrayConverter<>(SHORT_CONVERTER, Short[]::new);
    public static final Converter<List<Short>> SHORT_LIST_CONVERTER = new CollectionConverter<>(SHORT_CONVERTER, ArrayList::new);
    public static final Converter<Set<Short>> SHORT_SET_CONVERTER = new CollectionConverter<>(SHORT_CONVERTER, HashSet::new);

    public static final Converter<Integer> INTEGER_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::intValue);
    public static final Converter<Integer[]> INTEGER_ARRAY_CONVERTER = new ArrayConverter<>(INTEGER_CONVERTER, Integer[]::new);
    public static final Converter<List<Integer>> INTEGER_LIST_CONVERTER = new CollectionConverter<>(INTEGER_CONVERTER, ArrayList::new);
    public static final Converter<Set<Integer>> INTEGER_SET_CONVERTER = new CollectionConverter<>(INTEGER_CONVERTER, HashSet::new);

    public static final Converter<Long> LONG_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::longValue);
    public static final Converter<Long[]> LONG_ARRAY_CONVERTER = new ArrayConverter<>(LONG_CONVERTER, Long[]::new);
    public static final Converter<List<Long>> LONG_LIST_CONVERTER = new CollectionConverter<>(LONG_CONVERTER, ArrayList::new);
    public static final Converter<Set<Long>> LONG_SET_CONVERTER = new CollectionConverter<>(LONG_CONVERTER, HashSet::new);

    public static final Converter<Float> FLOAT_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::floatValue);
    public static final Converter<Float[]> FLOAT_ARRAY_CONVERTER = new ArrayConverter<>(FLOAT_CONVERTER, Float[]::new);
    public static final Converter<List<Float>> FLOAT_LIST_CONVERTER = new CollectionConverter<>(FLOAT_CONVERTER, ArrayList::new);
    public static final Converter<Set<Float>> FLOAT_SET_CONVERTER = new CollectionConverter<>(FLOAT_CONVERTER, HashSet::new);

    public static final Converter<Double> DOUBLE_CONVERTER = (val) -> NUMBER_CONVERTER.convert(val).map(Number::doubleValue);
    public static final Converter<Double[]> DOUBLE_ARRAY_CONVERTER = new ArrayConverter<>(DOUBLE_CONVERTER, Double[]::new);
    public static final Converter<List<Double>> DOUBLE_LIST_CONVERTER = new CollectionConverter<>(DOUBLE_CONVERTER, ArrayList::new);
    public static final Converter<Set<Double>> DOUBLE_SET_CONVERTER = new CollectionConverter<>(DOUBLE_CONVERTER, HashSet::new);

    @SuppressWarnings("unchecked")
    public static <T> @NotNull Converter<T> typeConverter(Class<T> type) {
        return (obj) -> {
            if (obj != null &&
                    type.isAssignableFrom(obj.getClass()))
                return Optional.of((T) obj);

            return Optional.empty();
        };
    }

    private Converters() {
    }

    private static final class Holder {
        private static final Converters INSTANCE = new Converters();
    }

    public static Converters getInstance() {
        return Holder.INSTANCE;
    }
}