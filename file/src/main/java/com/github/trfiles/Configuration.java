package com.github.trfiles;

import java.nio.file.Path;

/**
 * A {@link Configuration} represent the content of a file loaded into memory.<p>
 * A Configuration is internally organized with {@code Map<String, Object>} for each
 * {@link Section} inside it.
 * <p>
 * A configuration itself is a {@link Section}, and so it is represented with a
 * {@code Map<String, Object>} too.
 * <p>
 * Here a visual example, if we consider the following {@code JSON Configuration},
 * the root curly brackets represents the {@link Configuration} and the {@code 'meta'}, {@code 'resolution'}
 * and {@code 'element'} keys contains a {@link Section} as value:
 * <pre>
 *     {@code
 *          { // <-- This is the Configuration
 *               "meta": { // <-- This is Section
 *                 "format_version": "5.0",
 *                 "model_format": "free",
 *                 "box_uv": true
 *               },
 *               "resolution": { // <-- This is Section
 *                 "width": 128.0,
 *                 "height": 128.0
 *               },
 *               "element": { // <-- This is Section
 *                   "name": "cube",
 *                   "box_uv": false,
 *                   "render_order": "default",
 *                   "locked": false,
 *                   "allow_mirror_modeling": true,
 *                   "from": [
 *                     10.96977,
 *                     27.60521,
 *                     -10.25
 *                   ],
 *              }
 *          }
 *     }
 * </pre>
 */
public interface Configuration extends Section {

    /**
     * Load a new configuration from path.
     *
     * @param path the path to the file.
     */
    void load(Path path);

    /**
     * Write the current configuration to path.
     *
     * @param path the path to write to.
     */
    void save(Path path);

    /**
     * Reload the configuration from path.
     *
     * @param path the path to reload from.
     */
    void reload(Path path);

    /**
     * Get the configuration options.
     *
     * @return the configuration options
     */
    ConfigurationOptions getOptions();

}
