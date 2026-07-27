package com.github.trfiles.management.io.reader.path;

import com.github.trfiles.management.io.reader.stream.CharsStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CharsPathReader extends PathReader<char[]> {
    private CharsPathReader() {
    }

    private record Holder() {
        private static final CharsPathReader INSTANCE = new CharsPathReader();
    }

    public static CharsPathReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected char[] readOrThrown0(Path input, int from, int to) throws IOException {
        checkReadable(input);
        try (InputStream is = Files.newInputStream(input)) {
            return CharsStreamReader.getInstance()
                    .readOrThrown(is, from, to);
        }
    }

}
