package com.github.trfiles.management.io.reader.file;

import com.github.trfiles.management.io.reader.path.BytesPathReader;

import java.io.File;
import java.io.IOException;

public class BytesFileReader extends FileReader<byte[]> {
    private BytesFileReader() {
    }

    private record Holder() {
        private static final BytesFileReader INSTANCE = new BytesFileReader();
    }

    public static BytesFileReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected byte[] readOrThrown0(File input, int from, int to) throws IOException {
        return BytesPathReader.getInstance()
                .readOrThrown(input.toPath(), from, to);
    }
}
