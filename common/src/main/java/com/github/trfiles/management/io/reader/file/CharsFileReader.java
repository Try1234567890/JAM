package com.github.trfiles.management.io.reader.file;

import com.github.trfiles.management.io.reader.path.CharsPathReader;

import java.io.File;
import java.io.IOException;

public class CharsFileReader extends FileReader<char[]> {
    private CharsFileReader() {
    }

    private record Holder() {
        private static final CharsFileReader INSTANCE = new CharsFileReader();
    }

    public static CharsFileReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected char[] readOrThrown0(File input, int from, int to) throws IOException {
        return CharsPathReader.getInstance()
                .readOrThrown(input.toPath(), from, to);
    }

}
