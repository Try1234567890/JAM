package com.github.trfiles.management.io.readers.limited.fromPath;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderAsString;

import java.io.InputStream;
import java.nio.file.Path;

public class LimitedPathReaderAsString extends LimitedPathReader<String> {
    private StringPathReaderConfiguration configuration;

    private LimitedPathReaderAsString(Path input, StringPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringPathReaderConfiguration();
    }

    public static LimitedPathReaderAsString newInstance(Path input) {
        return new LimitedPathReaderAsString(input, new StringPathReaderConfiguration());
    }

    public static LimitedPathReaderAsString newInstance(Path input, StringPathReaderConfiguration configuration) {
        return new LimitedPathReaderAsString(input, configuration);
    }

    public LimitedPathReaderAsString withConfiguration(StringPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<String> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsString.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringPathReaderConfiguration extends PathReaderConfiguration<String> {
        private LimitedStreamReaderAsString.StreamReaderAsStringConfiguration streamReaderConfig;

        public StringPathReaderConfiguration withStreamReader(LimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        public LimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }
    }
}
