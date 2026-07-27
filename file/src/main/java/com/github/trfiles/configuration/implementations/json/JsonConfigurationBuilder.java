package com.github.trfiles.configuration.implementations.json;

import com.github.trfiles.configuration.FileConfigurationBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public class JsonConfigurationBuilder extends FileConfigurationBuilder {

    public JsonConfigurationBuilder() {
        super(JsonConfiguration.JsonEntry.getInstance());
    }

    @Override
    public JsonConfigurationBuilder withPath(Path path) {
        super.withPath(path);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withPath(File path) {
        super.withPath(path);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withPath(String path) {
        super.withPath(path);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(StringBuffer content) {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(StringBuilder content) {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(InputStream content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContentOrEmpty(InputStream content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(Reader content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContentOrEmpty(Reader content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(Path content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContentOrEmpty(Path content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(File content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContentOrEmpty(File content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public JsonConfigurationBuilder withContent(String content) {
        super.withContent(content);
        return this;
    }

    @Override
    public JsonConfiguration newConfiguration() throws IllegalArgumentException {
        Preconditions.simpleNotNull(getPath(), "Cannot resolve the FileConfiguration if the path is null.");

        return (JsonConfiguration)
                JsonConfiguration.ENTRY
                        .newInstance(getPath())
                        .withContent(getContent());
    }
}
