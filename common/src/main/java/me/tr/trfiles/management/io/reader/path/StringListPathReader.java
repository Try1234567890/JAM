package me.tr.trfiles.management.io.reader.path;

import me.tr.trfiles.management.io.reader.stream.StringListStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StringListPathReader extends PathReader<List<String>> {
    private StringListPathReader() {
    }

    private record Holder() {
        private static final StringListPathReader INSTANCE = new StringListPathReader();
    }

    public static StringListPathReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected List<String> readOrThrown0(Path input, int from, int to) throws IOException {
        checkReadable(input);
        try (InputStream is = Files.newInputStream(input)) {
            return StringListStreamReader.getInstance()
                    .readOrThrown(is, from, to);
        }
    }
}
