package com.github.trfiles.memory.systems.converter;

import com.github.trfiles.Section;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public record SectionsToMapsConverter(Section root) {
    public Map<String, Object> convert() {
        return convert(root.getEntries());
    }


    private Map<String, Object> convert(Map<String, Object> values) {
        final Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            String key = entry.getKey();
            Object value = convert(entry.getValue());

            result.put(key, value);
        }

        return result;
    }

    private Object convert(Object value) {
        if (value instanceof Section section) {
            Map<String, Object> values = section.getEntries();
            return convert(values);
        }
        if (value instanceof Object[] arr) {
            Object[] newArr = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = convert(arr[i]);
            }
            return newArr;
        }
        if (value instanceof Collection<?> coll) {
            Collection<Object> newColl = new LinkedList<>();
            for (Object item : coll) {
                newColl.add(convert(item));
            }
            return newColl;
        }
        if (value instanceof Map<?, ?> map) {
            Map<Object, Object> values = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = convert(entry.getKey());
                Object val = convert(entry.getValue());
                values.put(key, val);
            }
            return values;
        }
        return value;
    }
}













