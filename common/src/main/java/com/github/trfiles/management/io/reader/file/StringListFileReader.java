package com.github.trfiles.management.io.reader.file;

import com.github.trfiles.management.io.reader.path.StringListPathReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;

public class StringListFileReader extends FileReader<List<String>> {
    private StringListFileReader() {
    }

    private record Holder() {
        private static final StringListFileReader INSTANCE = new StringListFileReader();
    }

    public static StringListFileReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected List<String> readOrThrown0(File input, int from, int to) throws IOException, InvalidPathException {
        return StringListPathReader.getInstance()
                .readOrThrown(input.toPath(), from, to);
    }
}
