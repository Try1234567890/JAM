package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.managers.FileManager;
import com.github.trfiles.management.io.readers.InstanceReader;
import com.github.trfiles.management.io.readers.ReaderConfiguration;
import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class UnlimitedPathReader<O> extends InstanceReader<Path, O> {

    protected UnlimitedPathReader(Path input) {
        super(input);
    }

    @Override
    public abstract PathReaderConfiguration<O> getConfiguration();

    @Override
    protected O readEffectively(Path value, long offset, long length) throws Exception {
        checkReadable(value);

        try (InputStream is = FileManager.newInputStream(value)) {
            return toStreamReader(is).read(offset, length);
        }
    }

    protected abstract UnlimitedStreamReader<O> toStreamReader(InputStream is);

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public abstract static class PathReaderConfiguration<O> extends ReaderConfiguration<Path, O> {

    }
}
