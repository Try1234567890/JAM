package com.github.trfiles.memory;

import com.github.trfiles.ConfigurationOptions;
import com.github.trfiles.Configuration;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryConfiguration extends MemorySection implements Configuration {
    private final ConfigurationOptions options;

    public MemoryConfiguration() {
        this(new LinkedHashMap<>(), new ConfigurationOptions());
    }

    public MemoryConfiguration(Map<?, ?> values) {
        this(values, new ConfigurationOptions());
    }

    public MemoryConfiguration(ConfigurationOptions options) {
        this(new LinkedHashMap<>(), options);
    }

    public MemoryConfiguration(Map<?, ?> values,
                               ConfigurationOptions options) {
        this.options = options;
        super(values);
    }


    /**
     * @param path
     */
    @Override
    public void load(Path path) {

    }

    /**
     * @param path the path to write to.
     */
    @Override
    public void save(Path path) {

    }

    /**
     * @param path the path to reload from.
     */
    @Override
    public void reload(Path path) {

    }

    @Override
    public ConfigurationOptions getOptions() {
        return options;
    }
}
