package com.github.trfiles.management.io.reader.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringStreamReader extends StreamReader<String> {
    private StringStreamReader() {
    }

    private record Holder() {
        private static final StringStreamReader INSTANCE = new StringStreamReader();
    }

    public static StringStreamReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected String readOrThrown0(InputStream input, int from, int to) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (i < from) continue;
                if (i >= to) break;
                sb.append(line).append("\n");
                i++;
            }
            return sb.toString();
        }
    }
}
