package com.github.trfiles.configuration;

import com.github.trfiles.configuration.comments.Comment;
import com.github.trfiles.configuration.registry.ConfigurationEntry;
import com.github.trfiles.exceptions.InvalidConfigurationException;
import com.github.trfiles.management.FileExtension;
import com.github.trfiles.management.FileUtility;
import com.github.trfiles.management.io.reader.Readers;
import com.github.trfiles.memory.MemoryConfiguration;
import com.github.utilities.validators.Preconditions;

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

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise thrown a {@link IOException}.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     * @throws IOException if an error occurs while reading from the stream.
     */
    public FileConfiguration withContentFrom(Path path) throws InvalidConfigurationException, IOException {
        this.withContent(Readers.getStringPathReader().readOrThrown(path));
        return this;
    }

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise use the default empty content.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFromOrEmpty(Path path) throws InvalidConfigurationException {
        this.withContent(Readers.getStringPathReader().readOrDefault(path, getEntry().getEmpty()));
        return this;
    }

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise thrown a {@link IOException}.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     * @throws IOException if an error occurs while reading from the stream.
     */
    public FileConfiguration withContentFrom(File path) throws InvalidConfigurationException, IOException {
        withContentFrom(path.toPath());
        return this;
    }

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise use the default empty content.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFromOrEmpty(File path) throws InvalidConfigurationException {
        withContentFromOrEmpty(path.toPath());
        return this;
    }

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise thrown a {@link IOException}.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     * @throws IOException if an error occurs while reading from the stream.
     */
    public FileConfiguration withContentFrom(String path) throws InvalidConfigurationException, IOException {
        withContentFrom(new File(path));
        return this;
    }

    /**
     * Initialize this configuration from the given {@code path} if possible, otherwise use the default empty content.
     *
     * @param path the path
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFromOrEmpty(String path) throws InvalidConfigurationException {
        withContentFromOrEmpty(new File(path));
        return this;
    }


    /**
     * Initialize this configuration from the given {@code reader} if possible, otherwise thrown a {@link IOException}.
     *
     * @param reader the reader
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     * @throws IOException if an error occurs while reading from the stream.
     */
    public FileConfiguration withContentFrom(Reader reader) throws InvalidConfigurationException, IOException {
        this.withContent(Preconditions.simpleNotNull(reader, "The reader cannot be null").readAllAsString());
        return this;
    }

    /**
     * Initialize this configuration from the given {@code reader} if possible, otherwise use the default empty content.
     *
     * @param reader the reader
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFromOrEmpty(Reader reader) throws InvalidConfigurationException {
        try {
            this.withContent(Preconditions.simpleNotNull(reader, "The reader cannot be null").readAllAsString());
        } catch (IOException _) {
            this.withContent(getEntry().getEmpty());
        }
        return this;
    }

    /**
     * Initialize this configuration from the given {@code stream} if possible, otherwise thrown a {@link IOException}.
     *
     * @param stream the stream
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     * @throws IOException if an error occurs while reading from the stream.
     */
    public FileConfiguration withContentFrom(InputStream stream) throws InvalidConfigurationException, IOException {
        this.withContent(Readers.getStringStreamReader()
                .readOrThrown(Preconditions.simpleNotNull(stream, "The stream cannot be null!")));
        return this;
    }


    /**
     * Initialize this configuration from the given {@code stream} if possible, otherwise use the default empty content.
     *
     * @param stream the stream
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFromOrEmpty(InputStream stream) throws InvalidConfigurationException {
        this.withContent(Readers.getStringStreamReader()
                .readOrDefault(Preconditions.simpleNotNull(stream, "The stream cannot be null!"), getEntry().getEmpty()));
        return this;
    }


    /**
     * Initialize this configuration from the given {@code string builder}.
     *
     * @param sb the string builder
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFrom(StringBuilder sb) throws InvalidConfigurationException {
        this.withContent(Preconditions.simpleNotNull(sb, new StringBuilder(getEntry().getEmpty())).toString());
        return this;
    }

    /**
     * Initialize this configuration from the given {@code string buffer}.
     *
     * @param sb the string buffer
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration.
     */
    public FileConfiguration withContentFrom(StringBuffer sb) throws InvalidConfigurationException {
        this.withContent(Preconditions.simpleNotNull(sb, new StringBuffer(getEntry().getEmpty())).toString());
        return this;
    }

    /**
     * Initialize this configuration from the given {@code content} as string.
     *
     * @param content the content
     * @return this file configuration instance
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration from the given {@code content}.
     */
    public FileConfiguration withContent(String content) throws InvalidConfigurationException {
        if (isEmpty(content)) {
            // Doesn't make any sense to parse empty content.
            return this;
        }
        if (getOptions().PARSE_COMMENT.get()) {
            List<Comment> comments = parseComments(content);
            setComments(comments);
        }
        parseContent(content);
        return this;
    }

    private boolean isEmpty(String content) {
        return content == null || content.trim().isEmpty() || content.trim().equals(getEntry().getEmpty());
    }

    /**
     * (Re-)Initialize the comments of this configuration
     *
     * @param comments the comments to set
     */
    protected void setComments(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    /**
     * @return the options of this configuration.
     */
    @Override
    public FileConfigurationOptions getOptions() {
        return options;
    }

    /**
     * @return the comments of this configuration.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Parse this configuration from the given {@code content}.
     *
     * @param content The content
     * @throws InvalidConfigurationException if an error occurs while parsing the configuration from the given {@code content}.
     */
    protected abstract void parseContent(String content) throws InvalidConfigurationException;

    /**
     * Dumps the content of this {@link FileConfiguration} to a {@link String}.
     *
     * @return the content of this {@link FileConfiguration} as a {@link String}.
     */
    protected abstract String dumpContent();

    /**
     * Parse all the comments found in the given {@code content} following the rules of
     * this configuration.<p>
     * {@code This is used to initialize the comments, to retrieve them the respective getter must be used.}
     *
     * @param content The content
     * @return the list of {@link Comment} parsed.
     */
    protected abstract List<Comment> parseComments(String content);

    /**
     * Retrieves the {@link ConfigurationEntry} of this {@link FileConfiguration}.
     *
     * @return the {@link ConfigurationEntry} of this {@link FileConfiguration}.
     */
    public abstract ConfigurationEntry getEntry();

}
