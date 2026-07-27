package com.github.trfiles.configuration.implementations.toml;

import com.github.trfiles.configuration.FileConfigurationBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public class TomlConfigurationBuilder extends FileConfigurationBuilder {

    public TomlConfigurationBuilder() {
        super(TomlConfiguration.TomlEntry.getInstance());
    }

    @Override
    public TomlConfigurationBuilder withPath(Path path) {
        super.withPath(path);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withPath(File path) {
        super.withPath(path);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withPath(String path) {
        super.withPath(path);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(StringBuffer content) {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(StringBuilder content) {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(InputStream content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContentOrEmpty(InputStream content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(Reader content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContentOrEmpty(Reader content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(Path content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContentOrEmpty(Path content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(File content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContentOrEmpty(File content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public TomlConfigurationBuilder withContent(String content) {
        super.withContent(content);
        return this;
    }

    @Override
    public TomlConfiguration newConfiguration() throws IllegalArgumentException {
        Preconditions.simpleNotNull(getPath(), "Cannot resolve the FileConfiguration if the path is null.");

        return (TomlConfiguration)
                TomlConfiguration.ENTRY
                        .newInstance(getPath())
                        .withContent(getContent());
    }
}
