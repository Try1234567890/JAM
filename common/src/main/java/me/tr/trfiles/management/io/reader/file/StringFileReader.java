package me.tr.trfiles.management.io.reader.file;

import me.tr.trfiles.management.io.reader.path.StringPathReader;

import java.io.File;
import java.io.IOException;

public class StringFileReader extends FileReader<String> {
    private StringFileReader() {
    }

    private record Holder() {
        private static final StringFileReader INSTANCE = new StringFileReader();
    }

    public static StringFileReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected String readOrThrown0(File input, int from, int to) throws IOException {
        return StringPathReader.getInstance()
                .readOrThrown(input.toPath(), from, to);
    }
}
