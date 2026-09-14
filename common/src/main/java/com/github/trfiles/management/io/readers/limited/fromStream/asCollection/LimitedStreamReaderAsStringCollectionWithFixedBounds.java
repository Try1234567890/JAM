package com.github.trfiles.management.io.readers.limited.fromStream.asCollection;

import com.github.trfiles.management.io.readers.limited.fromStream.asArray.LimitedStreamReaderAsStringArrayWithFixedBounds;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class LimitedStreamReaderAsStringCollectionWithFixedBounds extends LimitedStreamReaderAsStringCollection {
    private StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration;

    private LimitedStreamReaderAsStringCollectionWithFixedBounds(InputStream input, StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration();
    }

    public static LimitedStreamReaderAsStringCollectionWithFixedBounds newInstance(InputStream input) {
        return new LimitedStreamReaderAsStringCollectionWithFixedBounds(input, new StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration());
    }

    public static LimitedStreamReaderAsStringCollectionWithFixedBounds newInstance(InputStream input, StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        return new LimitedStreamReaderAsStringCollectionWithFixedBounds(input, configuration);
    }

    public LimitedStreamReaderAsStringCollectionWithFixedBounds withConfiguration(StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<String> readEffectively(InputStream value, long offset, long length) throws Exception {
        String[] lines = LimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(value, getConfiguration().getArrayReaderConfig())
                .read(offset, length);

        Collection<String> newLines = getConfiguration().newCollection();
        newLines.addAll(Arrays.asList(lines));
        return newLines;
    }

    public static class StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration extends StreamReaderAsStringCollectionConfiguration {
        private LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration arrayReaderConfig;


        public StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration withArrayReader(LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
            this.arrayReaderConfig = configuration;
            return this;
        }

        public LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getArrayReaderConfig() {
            return arrayReaderConfig != null ? arrayReaderConfig : new LimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
        }

    }
}
