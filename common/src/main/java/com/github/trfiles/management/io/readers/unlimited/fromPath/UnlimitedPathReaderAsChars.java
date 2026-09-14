package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderAsChars;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsChars extends UnlimitedPathReader<Collection<char[]>> {
    private CharsPathReaderConfiguration configuration;

    private UnlimitedPathReaderAsChars(Path input, CharsPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new CharsPathReaderConfiguration();
    }

    public static UnlimitedPathReaderAsChars newInstance(Path input) {
        return new UnlimitedPathReaderAsChars(input, new CharsPathReaderConfiguration());
    }

    public static UnlimitedPathReaderAsChars newInstance(Path input, CharsPathReaderConfiguration configuration) {
        return new UnlimitedPathReaderAsChars(input, configuration);
    }

    public UnlimitedPathReaderAsChars withConfiguration(CharsPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public CharsPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<char[]>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsChars.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class CharsPathReaderConfiguration extends PathReaderConfiguration<Collection<char[]>> {
        private UnlimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration streamReaderConfig;

        public CharsPathReaderConfiguration withStreamReader(UnlimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        public UnlimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsChars.StreamReaderAsCharsConfiguration();
        }
    }
}
