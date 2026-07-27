package com.github.trfiles.management.io.writer.stream;

import com.github.trfiles.exceptions.CannotRetrieveSizeException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamToStreamWriter extends ToStreamWriter<InputStream> {
    private StreamToStreamWriter() {
    }

    private record Holder() {
        private static final StreamToStreamWriter INSTANCE = new StreamToStreamWriter();
    }

    public static StreamToStreamWriter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream value, @NotNull OutputStream out, int from, int to) throws IOException {
        int curr;

        while ((curr = value.read()) != -1) {
            out.write(curr);
        }
    }

    @Override
    protected int size(InputStream value) {
        try {
            return value.available();
        } catch (IOException e) {
            throw new CannotRetrieveSizeException("Cannot retrieve the size of the input stream!");
        }
    }
}
