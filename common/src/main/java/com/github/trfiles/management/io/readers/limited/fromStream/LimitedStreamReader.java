package com.github.trfiles.management.io.readers.limited.fromStream;

import com.github.trfiles.management.io.readers.InstanceReader;
import com.github.trfiles.management.io.readers.ReaderConfiguration;

import java.io.InputStream;

public abstract class LimitedStreamReader<O> extends InstanceReader<InputStream, O> {

    protected LimitedStreamReader(InputStream input) {
        super(input);
    }

    @Override
    protected long _size(InputStream value) throws Exception {
        return value.available();
    }

    public abstract static class StreamReaderConfiguration<O> extends ReaderConfiguration<InputStream, O> {

    }
}
