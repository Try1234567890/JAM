package com.github.trfiles.configuration;

import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.configuration.registry.ConfigurationRegistry;
import com.github.trfiles.exceptions.UnknownImplementationException;
import com.github.trfiles.management.FileExtension;
import com.github.trfiles.management.FileUtility;
import com.github.trfiles.management.io.reader.Readers;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConfigurationBuilder {
    private final ConfigurationEntry entry;
    private Path path;
    private String content = "";

    public FileConfigurationBuilder(ConfigurationEntry entry) {
        this.entry = Preconditions.simpleParameterNotNull(entry, "entry");
    }

    protected Path getPath() {
        return path;
    }

    protected String getContent() {
        return content;
    }

    protected ConfigurationEntry getEntry() {
        return entry;
    }

    public FileConfigurationBuilder withPath(Path path) {
        if (Preconditions.isSimpleNull(path)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.path = path;
        return this;
    }

    public FileConfigurationBuilder withPath(File path) {
        if (Preconditions.isSimpleNull(path)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.path = path.toPath();
        return this;
    }

    public FileConfigurationBuilder withPath(String path) {
        if (Preconditions.isSimpleNull(path)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.path = Paths.get(path);
        return this;
    }


    public FileConfigurationBuilder withContent(StringBuffer content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = content.toString();
        return this;
    }

    public FileConfigurationBuilder withContent(StringBuilder content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = content.toString();
        return this;
    }

    public FileConfigurationBuilder withContent(InputStream content) throws IOException {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringStreamReader().readOrThrown(content);
        return this;
    }

    public FileConfigurationBuilder withContentOrEmpty(InputStream content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringStreamReader().readOrDefault(content, getEntry().getEmpty());
        return this;
    }

    public FileConfigurationBuilder withContent(Reader content) throws IOException {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = content.readAllAsString();
        return this;
    }

    public FileConfigurationBuilder withContentOrEmpty(Reader content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        try {
            this.content = content.readAllAsString();
        } catch (IOException e) {
            this.content = "";
        }
        return this;
    }

    public FileConfigurationBuilder withContent(Path content) throws IOException {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringPathReader().readOrThrown(content);
        return this;
    }

    public FileConfigurationBuilder withContentOrEmpty(Path content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringPathReader().readOrDefault(content, getEntry().getEmpty());
        return this;
    }

    public FileConfigurationBuilder withContent(File content) throws IOException {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringFileReader().readOrThrown(content);
        return this;
    }

    public FileConfigurationBuilder withContentOrEmpty(File content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = Readers.getStringFileReader().readOrDefault(content, getEntry().getEmpty());
        return this;
    }

    public FileConfigurationBuilder withContent(String content) {
        if (Preconditions.isSimpleNull(content)) {
            // Returns here if the path is null, delegate the validation
            // to the #newConfiguration method.
            return this;
        }
        this.content = content;
        return this;
    }

    public FileConfiguration newConfiguration() throws IllegalArgumentException, UnknownImplementationException {
        if (Preconditions.isSimpleNull(path))
            throw new IllegalArgumentException("Cannot resolve the FileConfiguration if the path is null.");
        FileExtension extension = FileUtility.getExtension(path).orElseThrow(() ->
                new UnknownImplementationException("Cannot retrieve the extension of the '" + path + "' to resolve the implementation type."));
        ConfigurationEntry entry = ConfigurationRegistry.retrieve(extension).orElseThrow(() ->
                new UnknownImplementationException("Cannot find the implementation type for the '" + extension + "' extension."));
        return entry.newInstance(path).withContent(content);
    }


}
