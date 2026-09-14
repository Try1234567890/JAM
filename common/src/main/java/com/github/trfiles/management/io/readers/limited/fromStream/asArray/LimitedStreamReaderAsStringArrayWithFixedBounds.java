package com.github.trfiles.management.io.readers.limited.fromStream.asArray;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderAsString;

import java.io.InputStream;

public class LimitedStreamReaderAsStringArrayWithFixedBounds extends LimitedStreamReaderAsStringArray {
    private StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration;

    private LimitedStreamReaderAsStringArrayWithFixedBounds(InputStream input, StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
    }

    public static LimitedStreamReaderAsStringArrayWithFixedBounds newInstance(InputStream input) {
        return new LimitedStreamReaderAsStringArrayWithFixedBounds(input, new StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration());
    }

    public static LimitedStreamReaderAsStringArrayWithFixedBounds newInstance(InputStream input, StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        return new LimitedStreamReaderAsStringArrayWithFixedBounds(input, configuration);
    }

    public LimitedStreamReaderAsStringArrayWithFixedBounds withConfiguration(StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected String[] readEffectively(InputStream value, long offset, long length) throws Exception {
        checkIndexes(value, offset, length);

        String content = LimitedStreamReaderAsString.newInstance(value, getConfiguration().getStringReaderConfig())
                .read(offset, length);
        String lineSeparator = getConfiguration().getLinesSeparator();
        return content.split(lineSeparator);
    }

    public static class StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration extends StreamReaderAsStringArrayConfiguration {
        private LimitedStreamReaderAsString.StreamReaderAsStringConfiguration stringReaderConfig;

        public StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration withStringReader(LimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.stringReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStringReaderConfig() {
            return stringReaderConfig != null ? stringReaderConfig : new LimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }

    }
}
