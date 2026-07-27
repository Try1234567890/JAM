package com.github.trfiles.configuration.implementations.yaml;

import com.github.trfiles.configuration.FileConfigurationBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public class YamlConfigurationBuilder extends FileConfigurationBuilder {

    public YamlConfigurationBuilder() {
        super(YamlConfiguration.YamlEntry.getInstance());
    }

    @Override
    public YamlConfigurationBuilder withPath(Path path) {
        super.withPath(path);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withPath(File path) {
        super.withPath(path);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withPath(String path) {
        super.withPath(path);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(StringBuffer content) {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(StringBuilder content) {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(InputStream content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContentOrEmpty(InputStream content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(Reader content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContentOrEmpty(Reader content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(Path content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContentOrEmpty(Path content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(File content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContentOrEmpty(File content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public YamlConfigurationBuilder withContent(String content) {
        super.withContent(content);
        return this;
    }

    @Override
    public YamlConfiguration newConfiguration() throws IllegalArgumentException {
        Preconditions.simpleNotNull(getPath(), "Cannot resolve the FileConfiguration if the path is null.");

        return (YamlConfiguration)
                YamlConfiguration.ENTRY
                        .newInstance(getPath())
                        .withContent(getContent());
    }
}
