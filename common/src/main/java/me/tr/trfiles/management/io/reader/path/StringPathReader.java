package me.tr.trfiles.management.io.reader.path;

import me.tr.trfiles.management.io.reader.stream.StringStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringPathReader extends PathReader<String> {
    private StringPathReader() {
    }

    private record Holder() {
        private static final StringPathReader INSTANCE = new StringPathReader();
    }

    public static StringPathReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected String readOrThrown0(Path input, int from, int to) throws IOException {
        checkReadable(input);
        try (InputStream is = Files.newInputStream(input)) {
            return StringStreamReader.getInstance()
                    .readOrThrown(is, from, to);
        }
    }
}
