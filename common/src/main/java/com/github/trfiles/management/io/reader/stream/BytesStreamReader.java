package com.github.trfiles.management.io.reader.stream;

import java.io.IOException;
import java.io.InputStream;

public class BytesStreamReader extends StreamReader<byte[]> {
    private BytesStreamReader() {
    }

    private record Holder() {
        private static final BytesStreamReader INSTANCE = new BytesStreamReader();
    }

    public static BytesStreamReader getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected byte[] readOrThrown0(InputStream input, int from, int to) throws IOException {
        int len = (to - from);
        byte[] buf = new byte[len];
        int bytesRead = input.read(buf, from, len);

        if (bytesRead != len) {
            throw new IOException("Cannot read all content of provided input stream.");
        }

        return buf;
    }
}
