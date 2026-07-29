package com.github.trfiles.configuration.implementations.yaml;

import com.github.trfiles.ConfigurationOptions;
import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.configuration.PrefixedCommentParser;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.validators.Preconditions;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YamlConfiguration extends FileConfiguration {
    private static final String COMMENT_PREFIX = "#";
    public static final YamlEntry ENTRY = YamlEntry.getInstance();
    private final Yaml yaml;


    public YamlConfiguration(Path path, YamlConfigurationOptions options) {
        // Validate the options here to ensure that remains 'YamlConfigurationOptions'
        super(path, Preconditions.simpleNotNull(options, new YamlConfigurationOptions()));
        this.yaml = options.buildYAML();
    }

    public YamlConfiguration(Path path) {
        this(path, new YamlConfigurationOptions());
    }

    public static YamlConfigurationBuilder builder() {
        return new YamlConfigurationBuilder();
    }


    @Override
    protected void parseContent(String content) throws InvalidConfigurationException {
        try {
            Map<?, ?> map = yaml.loadAs(content, Map.class);
            withValues(toSections.convert(map));
        } catch (Throwable e) {
            throw new InvalidConfigurationException("An error occurs while loading the YAML configuration.", e);
        }
    }

    @Override
    protected String dumpContent() {
        try {
            Map<String, Object> map = toMaps.convert();
            return yaml.dump(map);
        } catch (Throwable e) {
            throw new RuntimeException("An error occurs while dumping the YAML configuration.", e);
        }
    }

    @Override
    protected List<Comment> parseComments(String content) {
        return new PrefixedCommentParser(COMMENT_PREFIX).parse(content);
    }


    public Yaml getYaml() {
        return yaml;
    }

    @Override
    public YamlEntry getEntry() {
        return ENTRY;
    }

    @Override
    public YamlConfigurationOptions getOptions() {
        return (YamlConfigurationOptions) super.getOptions();
    }

    /**
     * Singleton implementation of {@link ConfigurationEntry} for YAML configuration files.
     */
    public static class YamlEntry implements ConfigurationEntry {

        private YamlEntry() {
        }

        private static class Holder {
            private static final YamlEntry INSTANCE = new YamlEntry();
        }

        public static YamlEntry getInstance() {
            return Holder.INSTANCE;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public FileConfiguration newInstance(Path path) {
            return new YamlConfiguration(path);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<FileExtension> extensions() {
            return Set.of(
                    new FileExtension("yaml"),
                    new FileExtension("yml")
            );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getEmpty() {
            return "{}";
        }
    }
}
