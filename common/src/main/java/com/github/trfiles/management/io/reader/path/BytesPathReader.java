package com.github.trfiles.management.io.reader.path;

import com.github.trfiles.management.io.reader.stream.BytesStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BytesPathReader extends PathReader<byte[]> {
    private BytesPathReader() {
    }

    private record Holder() {
        private static final BytesPathReader INSTANCE = new BytesPathReader();
    }

    public static BytesPathReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected byte[] readOrThrown0(Path input, int from, int to) throws IOException {
        checkReadable(input);
        try (InputStream is = Files.newInputStream(input)) {
            return BytesStreamReader.getInstance()
                    .readOrThrown(is, from, to);
        }
    }
}
