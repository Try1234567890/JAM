package com.github.trfiles.configuration;

import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.trfiles.management.FileUtility;
import com.github.trfiles.management.io.reader.Readers;
import com.github.utilities.validators.Preconditions;
import com.github.trfiles.memory.MemoryConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class FileConfiguration extends MemoryConfiguration {
    private final Path path;
    private final FileConfigurationOptions options;
    private final List<Comment> comments;

    protected FileConfiguration(Path path,
                                FileConfigurationOptions options) {
        Preconditions.parameterNotNull(path, "path");
        this.options = Preconditions.simpleNotNull(options, new FileConfigurationOptions());

        if (this.options.FILE_VALIDATION.get()) {
            Preconditions.check(hasValidExtension(path, getEntry().extensions()) || getEntry().isValid(path),
                    "The path \"" + path + "\" has not a valid extension. Valid extensions: " + getEntry().extensions() + "!");
        }

        this.path = path;
        this.comments = new ArrayList<>();
    }

    protected FileConfiguration(Path path) {
        this(path, new FileConfigurationOptions());
    }

    private static boolean hasValidExtension(Path path,
                                             Set<FileExtension> extensions) {
        return FileUtility.getExtension(path)
                .map(extensions::contains)
                .orElse(false);
    }

    public Path getPath() {
        return path;
    }

    public FileConfiguration withContentFrom(Path path) throws InvalidConfigurationException, IOException {
        this.withContent(Readers.getStringPathReader().readOrThrown(path));
        return this;
    }

    public FileConfiguration withContentFromOrEmpty(Path path) throws InvalidConfigurationException {
        this.withContent(Readers.getStringPathReader().readOrDefault(path, getEntry().getEmpty()));
        return this;
    }

    public FileConfiguration withContentFrom(File path) throws InvalidConfigurationException, IOException {
        withContentFrom(path.toPath());
        return this;
    }

    public FileConfiguration withContentFromOrEmpty(File path) throws InvalidConfigurationException {
        withContentFromOrEmpty(path.toPath());
        return this;
    }

    public FileConfiguration withContentFrom(String path) throws InvalidConfigurationException, IOException {
        withContentFrom(new File(path));
        return this;
    }

    public FileConfiguration withContentFromOrEmpty(String path) throws InvalidConfigurationException {
        withContentFromOrEmpty(new File(path));
        return this;
    }

    public FileConfiguration withContentFrom(Reader reader) throws InvalidConfigurationException, IOException {
        this.withContent(Preconditions.simpleNotNull(reader, "The reader cannot be null").readAllAsString());
        return this;
    }

    public FileConfiguration withContentFromOrEmpty(Reader reader) throws InvalidConfigurationException {
        try {
            this.withContent(Preconditions.simpleNotNull(reader, "The reader cannot be null").readAllAsString());
        } catch (IOException _) {
            this.withContent(getEntry().getEmpty());
        }
        return this;
    }

    public FileConfiguration withContentFrom(InputStream stream) throws InvalidConfigurationException, IOException {
        this.withContent(Readers.getStringStreamReader()
                .readOrThrown(Preconditions.simpleNotNull(stream, "The stream cannot be null!")));
        return this;
    }

    public FileConfiguration withContentFromOrEmpty(InputStream stream) throws InvalidConfigurationException {
        this.withContent(Readers.getStringStreamReader()
                .readOrDefault(Preconditions.simpleNotNull(stream, "The stream cannot be null!"), getEntry().getEmpty()));
        return this;
    }

    public FileConfiguration withContentFrom(StringBuilder sb) throws InvalidConfigurationException {
        this.withContent(Preconditions.simpleNotNull(sb, new StringBuilder(getEntry().getEmpty())).toString());
        return this;
    }

    public FileConfiguration withContentFrom(StringBuffer sb) throws InvalidConfigurationException {
        this.withContent(Preconditions.simpleNotNull(sb, new StringBuffer(getEntry().getEmpty())).toString());
        return this;
    }

    public FileConfiguration withContent(String content) throws InvalidConfigurationException {
        if (isEmpty(content)) {
            // Doesn't make any sense to parse empty content.
            return this;
        }
        if (getOptions().PARSE_COMMENT.get()) {
            List<Comment> comments = parseComments(content);
            withComments(comments);
        }
        return parseContent(content);
    }

    private boolean isEmpty(String content) {
        return content == null || content.trim().isEmpty() || content.trim().equals(getEntry().getEmpty());
    }


    public FileConfiguration withComments(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        return this;
    }

    @Override
    public FileConfigurationOptions getOptions() {
        return options;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // protected methods - This is used to initialize the Configuration.
    protected abstract FileConfiguration parseContent(String content) throws InvalidConfigurationException;

    // protected methods - This is used to initialize the Comments,
    // to retrieve it the respective getters should be used.
    protected abstract List<Comment> parseComments(String content);

    public abstract ConfigurationEntry getEntry();

}
