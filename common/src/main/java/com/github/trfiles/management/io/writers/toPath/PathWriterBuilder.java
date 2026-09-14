package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.*;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class PathWriterBuilder {
    private final Path path;


    public PathWriterBuilder(Path path) {
        this.path = Preconditions.simpleParameterNotNull(path, "path");
    }

    public BytesPathWriter withBytes(byte... bytes) {
        Preconditions.simpleParameterNotNull(bytes, "bytes");
        return BytesPathWriter.newInstance(bytes, path);
    }

    public CharsPathWriter withChars(char... chars) {
        Preconditions.simpleParameterNotNull(chars, "chars");
        return CharsPathWriter.newInstance(chars, path);
    }

    public PathPathWriter withPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return PathPathWriter.newInstance(path, this.path);
    }

    public PathPathWriter withPath(File path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return withPath(path.toPath());
    }

    public PathPathWriter withPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return withPath(Paths.get(path));
    }

    public StreamPathWriter withStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return StreamPathWriter.newInstance(is, path);
    }

    public StringArrayPathWriter withStrings(String... strings) {
        Preconditions.simpleParameterNotNull(strings, "strings");
        return StringArrayPathWriter.newInstance(strings, path);
    }

    public StringCollectionPathWriter withStrings(Collection<String> strings) {
        Preconditions.simpleParameterNotNull(strings, "strings");
        return StringCollectionPathWriter.newInstance(strings, path);
    }

    public StringPathWriter withString(String string) {
        Preconditions.simpleParameterNotNull(string, "string");
        return StringPathWriter.newInstance(string, path);
    }
}