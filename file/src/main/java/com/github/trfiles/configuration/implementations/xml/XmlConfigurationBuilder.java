package com.github.trfiles.configuration.implementations.xml;

import com.github.trfiles.configuration.FileConfigurationBuilder;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public class XmlConfigurationBuilder extends FileConfigurationBuilder {

    public XmlConfigurationBuilder() {
        super(XmlConfiguration.XmlEntry.getInstance());
    }

    @Override
    public XmlConfigurationBuilder withPath(Path path) {
        super.withPath(path);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withPath(File path) {
        super.withPath(path);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withPath(String path) {
        super.withPath(path);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(StringBuffer content) {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(StringBuilder content) {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(InputStream content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContentOrEmpty(InputStream content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(Reader content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContentOrEmpty(Reader content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(Path content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContentOrEmpty(Path content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(File content) throws IOException {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContentOrEmpty(File content) {
        super.withContentOrEmpty(content);
        return this;
    }

    @Override
    public XmlConfigurationBuilder withContent(String content) {
        super.withContent(content);
        return this;
    }

    @Override
    public XmlConfiguration newConfiguration() throws IllegalArgumentException {
        Preconditions.simpleNotNull(getPath(), "Cannot resolve the FileConfiguration if the path is null.");

        return (XmlConfiguration)
                XmlConfiguration.ENTRY
                        .newInstance(getPath())
                        .withContent(getContent());
    }
}
