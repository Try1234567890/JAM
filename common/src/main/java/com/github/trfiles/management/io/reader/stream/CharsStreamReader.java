package com.github.trfiles.management.io.reader.stream;

import java.io.IOException;
import java.io.InputStream;

public class CharsStreamReader extends StreamReader<char[]> {
    private CharsStreamReader() {
    }

    private record Holder() {
        private static final CharsStreamReader INSTANCE = new CharsStreamReader();
    }

    public static CharsStreamReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected char[] readOrThrown0(InputStream input, int from, int to) throws IOException {
        int len = (to - from);
        byte[] buf = new byte[len];
        int bytesRead = input.read(buf, from, len);

        if (bytesRead != len) {
            throw new IOException("Cannot read all content of provided input stream.");
        }
        char[] chars = new char[len];

        for (int i = 0; i < len; i++) {
            chars[i] = (char) (buf[i] & 0xFF);
        }

        return chars;
    }

}
