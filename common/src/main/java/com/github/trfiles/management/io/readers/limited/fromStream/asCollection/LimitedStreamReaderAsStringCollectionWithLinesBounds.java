package com.github.trfiles.management.io.readers.limited.fromStream.asCollection;

import com.github.trfiles.management.io.readers.limited.fromStream.asArray.LimitedStreamReaderAsStringArrayWithLinesBounds;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class LimitedStreamReaderAsStringCollectionWithLinesBounds extends LimitedStreamReaderAsStringCollection {
    private StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration;

    private LimitedStreamReaderAsStringCollectionWithLinesBounds(InputStream input, StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration();
    }

    public static LimitedStreamReaderAsStringCollectionWithLinesBounds newInstance(InputStream input) {
        return new LimitedStreamReaderAsStringCollectionWithLinesBounds(input, new StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration());
    }

    public static LimitedStreamReaderAsStringCollectionWithLinesBounds newInstance(InputStream input, StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        return new LimitedStreamReaderAsStringCollectionWithLinesBounds(input, configuration);
    }

    public LimitedStreamReaderAsStringCollectionWithLinesBounds withConfiguration(StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    @Override
    public StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<String> readEffectively(InputStream value, long offset, long length) throws Exception {
        String[] lines = LimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(value, getConfiguration().getArrayReaderConfig())
                .read(offset, length);

        Collection<String> newLines = getConfiguration().newCollection();
        newLines.addAll(Arrays.asList(lines));
        return newLines;
    }

    public static class StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration extends StreamReaderAsStringCollectionConfiguration {
        private LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration arrayReaderConfig;

        public StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration withArrayReader(LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
            this.arrayReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getArrayReaderConfig() {
            return arrayReaderConfig != null ? arrayReaderConfig : new LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
        }

    }

}
