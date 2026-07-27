package com.github.trfiles.configuration.implementations.toml;

import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.configuration.PrefixedCommentParser;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.validators.Preconditions;
import tools.jackson.dataformat.toml.TomlMapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TomlConfiguration extends FileConfiguration {
    public static final String COMMENT_PREFIX = "#";
    public static final TomlEntry ENTRY = TomlEntry.getInstance();
    private final TomlMapper mapper;

    protected TomlConfiguration(Path path, TomlConfigurationOptions options) {
        super(path, Preconditions.simpleNotNull(options, new TomlConfigurationOptions()));
        this.mapper = options.buildToml();
    }

    protected TomlConfiguration(Path path) {
        this(path, new TomlConfigurationOptions());
    }

    @Override
    protected FileConfiguration parseContent(String content) throws InvalidConfigurationException {
        try {
            Map<?, ?> map = mapper.readValue(content, Map.class);
            withValues(toSections.convert(map));
        } catch (Throwable throwable) {
            throw new InvalidConfigurationException("An error occurs while loading Properties  configuration from " + getPath(), throwable);
        }
        return this;
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
    public TomlConfigurationOptions getOptions() {
        return (TomlConfigurationOptions) super.getOptions();
    }

    public static class TomlEntry implements ConfigurationEntry {
        private TomlEntry() {
        }

        private record Holder() {
            private static final TomlEntry INSTANCE = new TomlEntry();
        }

        public static TomlEntry getInstance() {
            return Holder.INSTANCE;
        }


        @Override
        public FileConfiguration newInstance(Path path) {
            return new TomlConfiguration(path);
        }

        @Override
        public Set<FileExtension> extensions() {
            return Set.of(
                    new FileExtension("toml")
            );
        }

        @Override
        public String getEmpty() {
            return "";
        }
    }
}
