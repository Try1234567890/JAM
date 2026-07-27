package com.github.trfiles.configuration.implementations.properties;

import com.github.trfiles.configuration.FileConfigurationBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public class PropertiesConfigurationBuilder extends FileConfigurationBuilder {

    public PropertiesConfigurationBuilder() {
        super(PropertiesConfiguration.PropertiesEntry.getInstance());
    }

    @Override
    public PropertiesConfigurationBuilder withPath(Path path) {
        super.withPath(path);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withPath(File path) {
        super.withPath(path);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withPath(String path) {
        super.withPath(path);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(StringBuffer content) {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(StringBuilder content) {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(InputStream content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContentOrEmpty(InputStream content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(Reader content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContentOrEmpty(Reader content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(Path content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContentOrEmpty(Path content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(File content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContentOrEmpty(File content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public PropertiesConfigurationBuilder withContent(String content) {
        super.withContent(content);
        return this;
    }

    @Override
    public PropertiesConfiguration newConfiguration() throws IllegalArgumentException {
        Preconditions.simpleNotNull(getPath(), "Cannot resolve the FileConfiguration if the path is null.");

        return (PropertiesConfiguration)
                PropertiesConfiguration.ENTRY
                        .newInstance(getPath())
                        .withContent(getContent());
    }
}
