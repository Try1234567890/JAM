package com.github.trfiles.management.io.reader.stream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringListStreamReader extends StreamReader<List<String>> {
    private StringListStreamReader() {
    }

    private record Holder() {
        private static final StringListStreamReader INSTANCE = new StringListStreamReader();
    }

    public static StringListStreamReader getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    protected List<String> readOrThrown0(InputStream input, int from, int to) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            List<String> result = new ArrayList<>();

            int i = 0;
            String line;
            while ((i >= from && i < to) && (line = reader.readLine()) != null) {
                result.add(line);
                i++;
            }


            return result;
        }
    }
}
