package com.github.trfiles.configuration.implementations.json;

import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.validators.Preconditions;
import com.google.gson.Gson;
import com.github.trfiles.configuration.FileConfiguration;
import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonConfiguration extends FileConfiguration {
    public static final JsonEntry ENTRY = JsonEntry.getInstance();
    private final Gson gson;

    protected JsonConfiguration(Path path, JsonConfigurationOptions options) {
        super(path, Preconditions.simpleNotNull(options, new JsonConfigurationOptions()));
        this.gson = options.buildGSON();
    }

    protected JsonConfiguration(Path path) {
        this(path, new JsonConfigurationOptions());
    }

    @Override
    protected FileConfiguration parseContent(String content) throws InvalidConfigurationException {
        try {
            Map<?, ?> map = gson.fromJson(content, Map.class);
            withValues(toSections.convert(map));
        } catch (Throwable throwable) {
            throw new InvalidConfigurationException("An error occurs while loading JSON configuration from " + getPath(), throwable);
        }
        return this;
    }

    @Override
    protected List<Comment> parseComments(String content) {
        return new JsonCommentParser(this).parse(content);
    }

    @Override
    public ConfigurationEntry getEntry() {
        return ENTRY;
    }

    @Override
    public JsonConfigurationOptions getOptions() {
        return (JsonConfigurationOptions) super.getOptions();
    }

    public static class JsonEntry implements ConfigurationEntry {
        private JsonEntry() {
        }

        private record Holder() {
            private static final JsonEntry INSTANCE = new JsonEntry();
        }

        public static JsonEntry getInstance() {
            return Holder.INSTANCE;
        }


        @Override
        public FileConfiguration newInstance(Path path) {
            return new JsonConfiguration(path);
        }

        @Override
        public Set<FileExtension> extensions() {
            return Set.of(
                    new FileExtension("json"),
                    new FileExtension("jsonc"),
                    new FileExtension("json5")
            );
        }

        @Override
        public String getEmpty() {
            return "{}";
        }
    }
}
