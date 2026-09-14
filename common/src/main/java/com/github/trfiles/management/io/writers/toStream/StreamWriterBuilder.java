package com.github.trfiles.management.io.writers.toStream;

import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class StreamWriterBuilder {
    private final OutputStream os;


    public StreamWriterBuilder(OutputStream os) {
        this.os = Preconditions.simpleParameterNotNull(os, "os");
    }

    public BytesStreamWriter withBytes(byte[] bytes) {
        Preconditions.simpleParameterNotNull(bytes, "bytes");
        return BytesStreamWriter.newInstance(bytes, os);
    }

    public CharsStreamWriter withChars(char[] chars) {
        Preconditions.simpleParameterNotNull(chars, "chars");
        return CharsStreamWriter.newInstance(chars, os);
    }

    public PathStreamWriter withPath(Path path) {
        Preconditions.simpleParameterNotNull(path, "path");
        return PathStreamWriter.newInstance(path, os);
    }

    public PathStreamWriter withPath(File path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return withPath(path.toPath());
    }

    public PathStreamWriter withPath(String path) throws InvalidPathException {
        Preconditions.simpleParameterNotNull(path, "path");
        return withPath(Paths.get(path));
    }

    public StreamStreamWriter withStream(InputStream is) {
        Preconditions.simpleParameterNotNull(is, "is");
        return StreamStreamWriter.newInstance(is, os);
    }

    public StringArrayStreamWriter withStrings(String... strings) {
        Preconditions.simpleParameterNotNull(strings, "strings");
        return StringArrayStreamWriter.newInstance(strings, os);
    }

    public StringCollectionStreamWriter withStrings(Collection<String> strings) {
        Preconditions.simpleParameterNotNull(strings, "strings");
        return StringCollectionStreamWriter.newInstance(strings, os);
    }

    public StringStreamWriter withString(String string) {
        Preconditions.simpleParameterNotNull(string, "string");
        return StringStreamWriter.newInstance(string, os);
    }
}