package com.github.trfiles.memory.systems.converter;

import com.github.trfiles.Section;
import com.github.trfiles.memory.MemoryConfiguration;
import com.github.trfiles.memory.MemorySection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public record MapsToSectionsConverter(Section root) {

    /**
     * Converts all {@link Map}s to {@link Section}s recursively inside the {@code map}.
     * <p>
     * This method handle nested structure like {@code Arrays} or {@code Collections}.
     *
     * @param map The root map to convert.
     * @return A new map with the value converted and ready to be assigned to sections values.
     */
    public Map<String, Object> convert(Map<?, ?> map) {
        final Map<String, Object> result = new LinkedHashMap<>();

        if (map == null || map.isEmpty()) {

            return result;
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            final String key = entry.getKey().toString();
            final Object rawValue = entry.getValue();
            final Object value = convert(rawValue, key, root);

            result.put(key, value);
        }

        return result;
    }

    /**
     * Helper method to convert all nested maps, if there are any, into Sections.
     * <p>
     *
     * @param value         The value to convert;
     * @param valueKey      The key of the value;
     * @param parentSection The section where the value is found.
     * @return The {@code value} with the Maps converted to Sections or a {@link Section} if {@code value} is a {@link Map}.
     */
    public Object convert(Object value, String valueKey, Section parentSection) {
        if (value instanceof Map<?, ?> map) {
            return new MemorySection(parentSection, valueKey, map);
        }
        return convertIterable(value);
    }

    /**
     * Recursively converts elements within iterable structures (arrays or collections),
     * transforming any nested {@link Map} instances into {@link MemoryConfiguration} objects.
     * <p>
     * This method is essential for processing lists or arrays that contain complex objects
     * (e.g., a list of configuration objects), ensuring that nested maps are properly
     * integrated into the configuration section hierarchy.
     * </p>
     *
     * @param value The object to convert; can be a map, an array, a collection, or a primitive/standard value.
     * @return A new array or collection containing the converted elements, a {@link MemoryConfiguration}
     * if the input was a map, or the original value if no conversion is required.
     */
    private Object convertIterable(Object value) {
        if (value instanceof Map<?, ?> map) {
            // If an isolated map is found within the iterable (lacking a direct key),
            // we wrap it in a MemoryConfiguration, which extends MemorySection and
            // doesn't have a key.
            return new MemoryConfiguration(map);
        }

        if (value instanceof Object[] arr) {
            Object[] newArr = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = convertIterable(arr[i]);
            }
            return newArr;
        }

        if (value instanceof Collection<?> coll) {
            Collection<Object> newColl = new ArrayList<>(coll.size());
            for (Object element : coll) {
                newColl.add(convertIterable(element));
            }
            return newColl;
        }

        // Note: Primitive arrays (e.g., int[], boolean[]) bypassed by the 'Object[]' check
        // cannot inherently contain Map instances, so they are returned as-is.
        return value;
    }
}
