package me.tr.trfiles.configuration.memory;

import me.tr.trfiles.configuration.ConfigOptions;
import me.tr.trfiles.configuration.Configuration;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryConfiguration extends MemorySection implements Configuration {
    private ConfigOptions options;

    public MemoryConfiguration() {
        this(new LinkedHashMap<>(), new ConfigOptions());
    }

    public MemoryConfiguration(Map<?, ?> values) {
        this(values, new ConfigOptions());
    }

    public MemoryConfiguration(ConfigOptions options) {
        this(new LinkedHashMap<>(), options);
    }

    public MemoryConfiguration(Map<?, ?> values,
                               ConfigOptions options) {
        super(values);
        this.options = options;
    }


    /**
     * @param file the file to write to.
     */
    @Override
    public void save(Path file) {

    }

    /**
     * @param file the file to reload from.
     */
    @Override
    public void reload(Path file) {

    }

    /**
     * @param file the file to move.
     * @param to   the destination file.
     */
    @Override
    public void move(Path file, Path to) {

    }

    /**
     * @param file the file to copy.
     * @param to   the destination file.
     */
    @Override
    public void copy(Path file, Path to) {

    }

    /**
     * @param file the file to delete.
     */
    @Override
    public void delete(Path file) {

    }

    @Override
    public ConfigOptions getOptions() {
        if (options == null) {
            this.options = new ConfigOptions();
            return options;
        }
        return options;
    }
}
