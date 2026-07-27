package me.tr.trfiles.configuration.memory;

import me.tr.trfiles.configuration.Configuration;
import me.tr.trfiles.configuration.Section;
import me.tr.trfiles.configuration.UnexpectedValueType;
import me.tr.trfiles.configuration.memory.systems.ValueEntry;
import me.tr.trfiles.configuration.memory.systems.ValueKey;
import me.tr.trfiles.configuration.memory.systems.ValueRetriever;
import me.tr.trfiles.configuration.memory.systems.converter.MapsToSectionsConverter;
import me.tr.trfiles.configuration.memory.systems.converter.SectionsToMapsConverter;

import java.util.*;

public class MemorySection implements Section {
    private final MapsToSectionsConverter toSections = new MapsToSectionsConverter(this);
    private final SectionsToMapsConverter toMaps = new SectionsToMapsConverter(this);
    private final Map<String, Object> values;
    private final Configuration root;
    private final String name;
    private final String absolutePath;
    private final Section parent;
    private final ValueRetriever retriever;

    public MemorySection(Map<?, ?> values,
                         Configuration root,
                         String name,
                         String absolutePath,
                         Section parent) {
        // Wrap values into a new LinkedHashMap to avoid
        // external references.
        this.values = toSections.convert(values);
        this.root = root;
        this.name = name;
        this.absolutePath = absolutePath;
        this.parent = parent;
        this.retriever = new ValueRetriever(this);
    }

    public MemorySection(Section parent, String name) {
        this(new LinkedHashMap<>(), parent.getRoot(), name, createAbsolutePath(parent, name), parent);
    }

    public MemorySection(Section parent, String name, Map<?, ?> values) {
        this(values, parent.getRoot(), name, createAbsolutePath(parent, name), parent);
    }

    protected MemorySection(Map<?, ?> values) {
        if (!(this instanceof Configuration config)) {
            throw new IllegalStateException("Cannot create a root Section if is not a Configuration.");
        }
        this.root = config;
        this.name = "";
        this.absolutePath = "";
        this.parent = null;
        this.values = toSections.convert(values);
        this.retriever = new ValueRetriever(this);
    }


    @Override
    public List<String> getKeys(boolean recursive) {
        final List<String> keys = new ArrayList<>(this.values.keySet());
        if (!recursive) return keys;

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();

            if (value instanceof Section sec) {
                List<String> subKeys = sec.getKeys(true);
                keys.addAll(subKeys);
            }
        }

        return keys;
    }

    @Override
    public Map<String, Object> getEntries() {
        return new LinkedHashMap<>(this.values);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public Configuration getRoot() {
        return root;
    }

    @Override
    public Section getParent() {
        return parent;
    }

    @Override
    public Map<String, Object> asMap() {
        return toMaps.convert();
    }

    @Override
    public ValueRetriever retriever() {
        return retriever;
    }

    /**
     * Retrieve the value from this {@link Section} with the infos provided by the {@link ValueKey}.
     *
     *
     * @param valueKey The {@link ValueKey} instance that contains the infos about the value to retrieve.
     * @return The {@link ValueEntry} instance of the value.
     * @param <T> The value type.
     * @throws UnexpectedValueType if any non-section value is found before reaching the value-key.
     */
    @Override
    public <T> ValueEntry<T> get(ValueKey<T> valueKey) throws UnexpectedValueType {
        String path = valueKey.path();
        char separator = getRoot().getOptions().PATH_SEPARATOR.get();
        Section current = this;
        int i1 = -1, i2;

        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String key = path.substring(i2, i1);
            Optional<Section> section = current.retriever().asSection(key).get();

            if (section.isEmpty()) {
                // This happens when the path contains a non-section
                // value before reaching the end of the path itself.
                throw new UnexpectedValueType("The value at key " + key + " inside the path " + path + " is not a Section. " +
                        "Cannot retrieve the value at " + path);
            }

            current = section.get();
        }

        String key = path.substring(i2);
        if (this == current) {
            Object value = values.get(key);
            Optional<T> converted = valueKey.converter().convert(value);
            return new ValueEntry<>(converted.orElse(null), valueKey.def());
        } else {
            ValueKey<T> newValueKey = new ValueKey<>(key, valueKey.converter(), valueKey.def());
            return current.get(newValueKey);
        }
    }

    /**
     *
     *
     * @param path  The path to the value.
     * @param value The value to set.
     * @param <T>   The type of the value.
     * @return The value set.
     */
    @Override
    public <T> ValueEntry<T> set(String path, T value) {
        PathSearchValue searchValue = getSectionAndKey(path);
        Section current = searchValue.section();
        String key = searchValue.key();

        if (this == current) {
            Object newValue = toSections.convert(value, key, this);
            values.put(key, newValue);
        } else {
            current.set(key, value);
        }

        return new ValueEntry<>(value, null);
    }

    /**
     * Create a new {@link Section} at the {@code path} with the provided {@code values}.
     *
     * @param path   The path to the new section.
     * @param values The values to set in the new section.
     * @return The section created.
     */
    @Override
    public Section newSection(String path, Map<?, ?> values) {
        PathSearchValue searchValue = getSectionAndKey(path);
        Section current = searchValue.section();
        String key = searchValue.key();

        if (this == current) {
            Section result = new MemorySection(current, key, values);
            this.values.put(key, result);
            return result;
        }

        return current.newSection(key);
    }

    /**
     * Retrieve the key and its section from the {@code path}.
     *
     * @param path The path.
     * @return An object containing the section and key.
     */
    private PathSearchValue getSectionAndKey(String path) {
        char separator = getRoot().getOptions().PATH_SEPARATOR.get();
        Section current = this;
        int i1 = -1, i2;

        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String key = path.substring(i2, i1);
            Optional<Section> section = current.retriever().asSection(key).get();

            if (section.isEmpty()) {
                current = current.newSection(key);
            } else {
                current = section.get();
            }
        }
        String key = path.substring(i2);

        return new PathSearchValue(current, key);
    }

    @Override
    public String toString() {
        return asMap().toString();
    }

    /**
     * Create the absolute path to the {@code key} key from the {@code root} section.
     *
     * @param section The section that contains the key.
     * @param key     The key name.
     * @return The absolute path starting from the root to {@code key}.
     */
    public static String createAbsolutePath(Section section, String key) {
        return createRelativePath(section, key, section.getRoot());
    }

    /**
     * Create the relative path that starts from the {@code relativeTo} section
     * and ends at the {@code key} of its {@code section}.
     *
     * @param section    The section that contains the key.
     * @param key        The key name.
     * @param relativeTo The section to start from.
     * @return The relative path starting from {@code relativeTo} to {@code key}.
     */
    public static String createRelativePath(Section section, String key, Section relativeTo) {
        StringBuilder sb = new StringBuilder();

        char separator = section.getRoot().getOptions().PATH_SEPARATOR.get();
        Section current = section;

        /*if current is null, it means that the parent is the root or is inside a collection.*/
        while (current != relativeTo && current != null) {
            String currentName = current.getName();
            appendKey(sb, currentName, separator);
            current = current.getParent();
        }

        appendKey(sb, key, separator);
        return sb.toString();
    }

    /**
     * Private helper method to append a new key to the {@link StringBuilder}
     * while building a new path with {@link #createRelativePath(Section, String, Section)}.
     *
     * @param sb        The result {@link StringBuilder}
     * @param key       The key to append
     * @param separator The separator between keys.
     */
    private static void appendKey(StringBuilder sb, String key, char separator) {
        if (!sb.isEmpty()) sb.append(separator);
        sb.append(key);
    }

    /**
     * This helper record is used to store the result of a path search.
     *
     * @param section The parent section of the {@code key}
     * @param key     The key that was searched.
     */
    private record PathSearchValue(Section section, String key) {
    }
}








