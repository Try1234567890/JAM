package com.github.trfiles.memory.systems;

import com.github.trfiles.Section;
import com.github.trfiles.exceptions.UnexpectedValueType;
import com.github.trfiles.memory.systems.converter.values.Converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class ValueRetriever {
    private final Section section;

    public ValueRetriever(Section section) {
        this.section = section;
    }

    public ValueEntry<Object> asObject(String path) {
        return get(new ValueKey<>(path, Converters.OBJECT_CONVERTER));
    }

    public ValueEntry<Object[]> asObjectArray(String path) {
        return get(new ValueKey<>(path, Converters.OBJECT_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Object>> asObjectList(String path) {
        return get(new ValueKey<>(path, Converters.OBJECT_LIST_CONVERTER));
    }

    public ValueEntry<Set<Object>> asObjectSet(String path) {
        return get(new ValueKey<>(path, Converters.OBJECT_SET_CONVERTER));
    }

    public ValueEntry<Boolean> asBoolean(String path) {
        return get(new ValueKey<>(path, Converters.BOOLEAN_CONVERTER));
    }

    public ValueEntry<Boolean[]> asBooleanArray(String path) {
        return get(new ValueKey<>(path, Converters.BOOLEAN_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Boolean>> asBooleanList(String path) {
        return get(new ValueKey<>(path, Converters.BOOLEAN_LIST_CONVERTER));
    }

    public ValueEntry<Set<Boolean>> asBooleanSet(String path) {
        return get(new ValueKey<>(path, Converters.BOOLEAN_SET_CONVERTER));
    }

    public ValueEntry<Byte> asByte(String path) {
        return get(new ValueKey<>(path, Converters.BYTE_CONVERTER));
    }

    public ValueEntry<Byte[]> asByteArray(String path) {
        return get(new ValueKey<>(path, Converters.BYTE_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Byte>> asByteList(String path) {
        return get(new ValueKey<>(path, Converters.BYTE_LIST_CONVERTER));
    }

    public ValueEntry<Set<Byte>> asByteSet(String path) {
        return get(new ValueKey<>(path, Converters.BYTE_SET_CONVERTER));
    }

    public ValueEntry<Short> asShort(String path) {
        return get(new ValueKey<>(path, Converters.SHORT_CONVERTER));
    }

    public ValueEntry<Short[]> asShortArray(String path) {
        return get(new ValueKey<>(path, Converters.SHORT_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Short>> asShortList(String path) {
        return get(new ValueKey<>(path, Converters.SHORT_LIST_CONVERTER));
    }

    public ValueEntry<Set<Short>> asShortSet(String path) {
        return get(new ValueKey<>(path, Converters.SHORT_SET_CONVERTER));
    }

    public ValueEntry<Integer> asInteger(String path) {
        return get(new ValueKey<>(path, Converters.INTEGER_CONVERTER));
    }

    public ValueEntry<Integer[]> asIntegerArray(String path) {
        return get(new ValueKey<>(path, Converters.INTEGER_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Integer>> asIntegerList(String path) {
        return get(new ValueKey<>(path, Converters.INTEGER_LIST_CONVERTER));
    }

    public ValueEntry<Set<Integer>> asIntegerSet(String path) {
        return get(new ValueKey<>(path, Converters.INTEGER_SET_CONVERTER));
    }

    public ValueEntry<Long> asLong(String path) {
        return get(new ValueKey<>(path, Converters.LONG_CONVERTER));
    }

    public ValueEntry<Long[]> asLongArray(String path) {
        return get(new ValueKey<>(path, Converters.LONG_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Long>> asLongList(String path) {
        return get(new ValueKey<>(path, Converters.LONG_LIST_CONVERTER));
    }

    public ValueEntry<Set<Long>> asLongSet(String path) {
        return get(new ValueKey<>(path, Converters.LONG_SET_CONVERTER));
    }

    public ValueEntry<Float> asFloat(String path) {
        return get(new ValueKey<>(path, Converters.FLOAT_CONVERTER));
    }

    public ValueEntry<Float[]> asFloatArray(String path) {
        return get(new ValueKey<>(path, Converters.FLOAT_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Float>> asFloatList(String path) {
        return get(new ValueKey<>(path, Converters.FLOAT_LIST_CONVERTER));
    }

    public ValueEntry<Set<Float>> asFloatSet(String path) {
        return get(new ValueKey<>(path, Converters.FLOAT_SET_CONVERTER));
    }

    public ValueEntry<Double> asDouble(String path) {
        return get(new ValueKey<>(path, Converters.DOUBLE_CONVERTER));
    }

    public ValueEntry<Double[]> asDoubleArray(String path) {
        return get(new ValueKey<>(path, Converters.DOUBLE_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Double>> asDoubleList(String path) {
        return get(new ValueKey<>(path, Converters.DOUBLE_LIST_CONVERTER));
    }

    public ValueEntry<Set<Double>> asDoubleSet(String path) {
        return get(new ValueKey<>(path, Converters.DOUBLE_SET_CONVERTER));
    }

    public ValueEntry<BigInteger> asBigInteger(String path) {
        return get(new ValueKey<>(path, Converters.BIG_INTEGER_CONVERTER));
    }

    public ValueEntry<BigInteger[]> asBigIntegerArray(String path) {
        return get(new ValueKey<>(path, Converters.BIG_INTEGER_ARRAY_CONVERTER));
    }

    public ValueEntry<List<BigInteger>> asBigIntegerList(String path) {
        return get(new ValueKey<>(path, Converters.BIG_INTEGER_LIST_CONVERTER));
    }

    public ValueEntry<Set<BigInteger>> asBigIntegerSet(String path) {
        return get(new ValueKey<>(path, Converters.BIG_INTEGER_SET_CONVERTER));
    }

    public ValueEntry<BigDecimal> asBigDecimal(String path) {
        return get(new ValueKey<>(path, Converters.BIG_DECIMAL_CONVERTER));
    }

    public ValueEntry<BigDecimal[]> asBigDecimalArray(String path) {
        return get(new ValueKey<>(path, Converters.BIG_DECIMAL_ARRAY_CONVERTER));
    }

    public ValueEntry<List<BigDecimal>> asBigDecimalList(String path) {
        return get(new ValueKey<>(path, Converters.BIG_DECIMAL_LIST_CONVERTER));
    }

    public ValueEntry<Set<BigDecimal>> asBigDecimalSet(String path) {
        return get(new ValueKey<>(path, Converters.BIG_DECIMAL_SET_CONVERTER));
    }

    public ValueEntry<String> asString(String path) {
        return get(new ValueKey<>(path, Converters.STRING_CONVERTER));
    }

    public ValueEntry<String[]> asStringArray(String path) {
        return get(new ValueKey<>(path, Converters.STRING_ARRAY_CONVERTER));
    }

    public ValueEntry<List<String>> asStringList(String path) {

        return get(new ValueKey<>(path, Converters.STRING_LIST_CONVERTER));
    }

    public ValueEntry<Set<String>> asStringSet(String path) {

        return get(new ValueKey<>(path, Converters.STRING_SET_CONVERTER));
    }

    public ValueEntry<Section> asSection(String path) {
        return get(new ValueKey<>(path, Converters.SECTION_CONVERTER));
    }

    public ValueEntry<Section[]> asSectionArray(String path) {
        return get(new ValueKey<>(path, Converters.SECTION_ARRAY_CONVERTER));
    }

    public ValueEntry<List<Section>> asSectionList(String path) {
        return get(new ValueKey<>(path, Converters.SECTION_LIST_CONVERTER));
    }

    /**
     * Retrieve the value at {@code path} inside the configuration as a set of sections.
     * <p>
     *
     *
     * @param path the path to the value
     * @return the {@link ValueEntry} found at the {@code key}
     * @throws UnexpectedValueType if any non-section value is found before reaching the value-key.
     */
    public ValueEntry<Set<Section>> asSectionSet(String path) {
        return get(new ValueKey<>(path, Converters.SECTION_SET_CONVERTER));
    }

    /**
     * Retrieve the value using the {@code key}.
     *
     * @param key the {@link ValueKey} to use
     * @param <T> The expected value type
     * @return the {@link ValueEntry} found at the {@code key}
     * @throws UnexpectedValueType if any non-section value is found before reaching the value-key.
     */
    public <T> ValueEntry<T> get(ValueKey<T> key) {
        return section.get(key);
    }
}


































