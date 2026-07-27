package com.github.trfiles.management.io.streaming.stream;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamToStreamStreaming extends StreamStreaming<OutputStream> {
    private StreamToStreamStreaming() {
    }

    private record Holder() {
        private static final StreamToStreamStreaming INSTANCE = new StreamToStreamStreaming();
    }

    public static StreamToStreamStreaming getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void writeRangeOrThrown0(@NotNull InputStream value, @NotNull OutputStream out, int from, int to) throws IOException {
        value.skip(from);
        for (int i = from; i < (size(value) - to); i++) {
            int currByte = value.read();

            if (currByte == -1) {
                // Should not happen because public methods check for indexes.
                throw new IOException("Stream ended before the expected size!");
            }

            out.write(currByte);
        }
    }
}
