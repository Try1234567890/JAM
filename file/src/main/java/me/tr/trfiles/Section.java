package me.tr.trfiles.configuration;

import me.tr.trfiles.configuration.memory.systems.ValueEntry;
import me.tr.trfiles.configuration.memory.systems.ValueKey;
import me.tr.trfiles.configuration.memory.systems.ValueRetriever;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@link Section} interface represents a specific node or block within a {@link Configuration}.
 * <p>
 * Internally, a {@code Section} is backed by a {@code Map<String, Object>}, where the {@link String}
 * keys act as identifiers used to retrieve the associated values.
 * </p>
 * <h3>Example: Named Sections</h3>
 * In the following JSON configuration, the {@code "meta"} key maps to a nested object,
 * which is represented as a {@link Section}:
 * <pre>
 *     {@code
 *          {
 *              "meta": {  // <--- This is a Section!
 *                  "format_version": "5.0",
 *                  "model_format": "free",
 *                  "box_uv": true
 *              },
 *              "name": "example_model",
 *              "visible_box": [1.0, 1.0, 0.0]
 *          }
 *     }
 * </pre>
 * <h3>Special Case: Anonymous Sections in Collections</h3>
 * A special case occurs when configuration blocks are nested inside a collection (such as a list or array).
 * Because these blocks are positional elements rather than key-value pairs, they lack an identifying key.
 * To handle this, they are represented as standalone {@link Configuration} instances instead of {@link Section}s.
 * <p>
 * For example, consider this JSON structure:
 * </pre>
 * <pre>
 *     {@code
 *          {
 *              "elements": [
 *                  { // <--- This is a Configuration, not a Section!
 *                      "name": "cube",
 *                      "locked": false,
 *                      "color": 0.0
 *                  },
 *                  { // <--- This is a Configuration, not a Section!
 *                      "name": "sphere",
 *                      "locked": true,
 *                      "color": 1.0
 *                  }
 *              ]
 *          }
 *      }
 * </pre>
 * <p>
 * From a functional standpoint, this distinction is seamless since {@link Configuration} extends {@link Section}.
 * However, because a {@link Configuration} conceptually represents a root or independent context-meaning it
 * has no associated key and no parent {@link Section}, it serves as the perfect abstraction for anonymous,
 * unkeyed blocks inside collections.
 */
public interface Section {

    /**
     * Retrieves the keys of this section, and if {@code recursive} is true,
     * it will also retrieve the keys of all child sections.
     *
     * @param recursive if this is {@code true} research recursively.
     * @return A List of all retrieved keys.
     */
    List<String> getKeys(boolean recursive);

    /**
     * Retrieves the entries of this section.
     *
     * @return A copy of key-value pairs of the section.
     */
    Map<String, Object> getEntries();

    /**
     * Retrieve the parent {@link Section} of this section.
     * <p>
     * The parent section is null if this is a {@link Configuration}
     * or if this section is inside a collection.
     *
     * @return The parent section, or null if this is a {@link Configuration}
     * or if this section is inside a collection.
     */
    @Nullable Section getParent();

    /**
     * Retrieve the name of this section.
     * <p>
     * The name is null if this is a {@link Configuration}
     * or if this section is inside a collection.
     *
     * @return The name of this section, or an empty String if this is a {@link Configuration}
     * or if this section is inside a collection.
     */
    String getName();

    /**
     * Retrieve the absolute path of this section.
     * The {@code absolute path} of a section is a String of Section names
     * from the root {@link Configuration} ({@link #getRoot()}) to this one separated
     * with the {@link ConfigOptions#PATH_SEPARATOR} of the configuration options.
     * <p>
     * The absolute path is null if this is a {@link Configuration}
     * or if this section is inside a collection.
     *
     * @return The absolute path of this section, or an empty String if this is a {@link Configuration}
     * or if this section is inside a collection.
     */
    String getAbsolutePath();

    /**
     * Retrieve the {@code root section}, or the {@link Configuration} in other words, of
     * this section.
     * <p> If this section is the root section, then it returns itself. </p>
     *
     * @return The root section of this section.
     */
    Configuration getRoot();

    /**
     * Check if this section is the root section.
     *
     * @return {@code true} if this section is the root section, {@code false} otherwise.
     */
    default boolean isRoot() {
        return this == getRoot();
    }

    /**
     * Convert this section to a Map.<p>
     * This performs a recursive conversion of the section and its child sections.
     *
     * @return A Map representation of this section.
     */
    Map<String, Object> asMap();

    /**
     * Retrieve the {@link ValueRetriever} system of this section.
     *
     * @return the {@link ValueRetriever} system of this section.
     */
    ValueRetriever retriever();


    /**
     * Retrieve the value from this {@link Section} with the infos provided by the {@link ValueKey}.
     *
     *
     * @param valueKey The {@link ValueKey} instance that contains the infos about the value to retrieve.
     * @return The {@link ValueEntry} instance of the value.
     * @param <T> The value type.
     * @throws UnexpectedValueType if any non-section value is found before reaching the value-key.
     */
    <T> ValueEntry<T> get(ValueKey<T> valueKey) throws UnexpectedValueType;

    /**
     * Set the value at the specified path.<p>
     * If the path contains intermediate sections that do not exist, they will be created.
     *
     * @param path The path to the value.
     * @param value The value to set.
     * @param <T> The type of the value.
     * @return The value entry of the new value.
     */
    <T> ValueEntry<T> set(String path, T value);


    /**
     * Creates a new section at the specified path with the given values. If the section already exists,
     * it will be overwritten with the new values.
     *
     * @param path The path to the new section.
     * @param values The values to set in the new section.
     * @return The newly created section.
     */
    Section newSection(String path, Map<?, ?> values);

    /**
     * Creates a new section at the specified path with the given values.
     * If the section already exists, it will be overwritten with an empty one.
     *
     * @param path The path to the new section.
     * @return The newly created section.
     */
    default Section newSection(String path) {
        return newSection(path, new LinkedHashMap<>());
    }

}