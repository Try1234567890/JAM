package com.github.trfiles.configuration.implementations.properties;

import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.configuration.PrefixedCommentParser;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.validators.Preconditions;
import tools.jackson.dataformat.javaprop.JavaPropsMapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PropertiesConfiguration extends FileConfiguration {
    public static final String COMMENT_PREFIX = "#";
    public static final PropertiesEntry ENTRY = PropertiesEntry.getInstance();
    private final JavaPropsMapper mapper;


    protected PropertiesConfiguration(Path path, PropertiesConfigurationOptions options) {
        super(path, Preconditions.simpleNotNull(options, new PropertiesConfigurationOptions()));
        this.mapper = options.buildMapper();
    }

    protected PropertiesConfiguration(Path path) {
        this(path, new PropertiesConfigurationOptions());
    }

    @Override
    protected void parseContent(String content) throws InvalidConfigurationException {
        try {
            Map<?, ?> map = mapper.readValue(content, Map.class);
            withValues(toSections.convert(map));
        } catch (Throwable throwable) {
            throw new InvalidConfigurationException("An error occurs while loading Properties  configuration from " + getPath(), throwable);
        }
    }

    @Override
    protected String dumpContent() {
        try {
            Map<String, Object> map = toMaps.convert();
            return mapper.writer().writeValueAsString(map);
        } catch (Throwable e) {
            throw new RuntimeException("An error occurs while dumping the Properties configuration.", e);
        }
    }

    @Override
    protected List<Comment> parseComments(String content) {
        return new PrefixedCommentParser(COMMENT_PREFIX).parse(content);
    }

    @Override
    public ConfigurationEntry getEntry() {
        return ENTRY;
    }

    @Override
    public PropertiesConfigurationOptions getOptions() {
        return (PropertiesConfigurationOptions) super.getOptions();
    }

    public static class PropertiesEntry implements ConfigurationEntry {
        private PropertiesEntry() {
        }

        private record Holder() {
            private static final PropertiesEntry INSTANCE = new PropertiesEntry();
        }

        public static PropertiesEntry getInstance() {
            return Holder.INSTANCE;
        }


        @Override
        public FileConfiguration newInstance(Path path) {
            return new PropertiesConfiguration(path);
        }

        @Override
        public Set<FileExtension> extensions() {
            return Set.of(
                    new FileExtension("prop"),
                    new FileExtension("properties"),
                    new FileExtension("env")
            );
        }

        @Override
        public String getEmpty() {
            return "";
        }
    }
}
