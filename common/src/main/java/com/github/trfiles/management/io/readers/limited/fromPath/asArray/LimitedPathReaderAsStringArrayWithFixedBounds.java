package com.github.trfiles.management.io.readers.limited.fromPath.asArray;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.asArray.LimitedStreamReaderAsStringArrayWithFixedBounds;

import java.io.InputStream;
import java.nio.file.Path;

public class LimitedPathReaderAsStringArrayWithFixedBounds extends LimitedPathReaderAsStringArray {
    private StringArrayPathReaderWithFixedBoundsConfiguration configuration;

    private LimitedPathReaderAsStringArrayWithFixedBounds(Path input, StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringArrayPathReaderWithFixedBoundsConfiguration();
    }

    public static LimitedPathReaderAsStringArrayWithFixedBounds newInstance(Path input) {
        return new LimitedPathReaderAsStringArrayWithFixedBounds(input, new StringArrayPathReaderWithFixedBoundsConfiguration());
    }

    public static LimitedPathReaderAsStringArrayWithFixedBounds newInstance(Path input, StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        return new LimitedPathReaderAsStringArrayWithFixedBounds(input, configuration);
    }

    public LimitedPathReaderAsStringArrayWithFixedBounds withConfiguration(StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringArrayPathReaderWithFixedBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<String[]> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringArrayPathReaderWithFixedBoundsConfiguration extends StringArrayPathReaderConfiguration {
        private LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration streamReaderConfig;

        public StringArrayPathReaderWithFixedBoundsConfiguration withStreamReader(LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
        }

    }
}
