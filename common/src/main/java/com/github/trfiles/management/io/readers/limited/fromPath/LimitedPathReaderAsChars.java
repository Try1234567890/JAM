package com.github.trfiles.management.io.readers.limited.fromPath;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderAsChars;

import java.io.InputStream;
import java.nio.file.Path;

public class LimitedPathReaderAsChars extends LimitedPathReader<char[]> {
    private CharsPathReaderConfiguration configuration;

    private LimitedPathReaderAsChars(Path input, CharsPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new CharsPathReaderConfiguration();
    }

    public static LimitedPathReaderAsChars newInstance(Path input) {
        return new LimitedPathReaderAsChars(input, new CharsPathReaderConfiguration());
    }

    public static LimitedPathReaderAsChars newInstance(Path input, CharsPathReaderConfiguration configuration) {
        return new LimitedPathReaderAsChars(input, configuration);
    }

    public LimitedPathReaderAsChars withConfiguration(CharsPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public CharsPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<char[]> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsChars.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class CharsPathReaderConfiguration extends PathReaderConfiguration<char[]> {
        private LimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration streamReaderConfig;

        public CharsPathReaderConfiguration withStreamReader(LimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        public LimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration();
        }
    }
}
