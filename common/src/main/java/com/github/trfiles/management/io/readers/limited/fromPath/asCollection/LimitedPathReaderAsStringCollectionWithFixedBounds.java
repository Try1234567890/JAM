package com.github.trfiles.management.io.readers.limited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.asCollection.LimitedStreamReaderAsStringCollectionWithFixedBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class LimitedPathReaderAsStringCollectionWithFixedBounds extends LimitedPathReaderAsStringCollection {
    private StringCollectionPathReaderWithFixedBoundsConfiguration configuration;

    private LimitedPathReaderAsStringCollectionWithFixedBounds(Path input, StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringCollectionPathReaderWithFixedBoundsConfiguration();
    }

    public static LimitedPathReaderAsStringCollectionWithFixedBounds newInstance(Path input) {
        return new LimitedPathReaderAsStringCollectionWithFixedBounds(input, new StringCollectionPathReaderWithFixedBoundsConfiguration());
    }

    public static LimitedPathReaderAsStringCollectionWithFixedBounds newInstance(Path input, StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        return new LimitedPathReaderAsStringCollectionWithFixedBounds(input, configuration);
    }

    public LimitedPathReaderAsStringCollectionWithFixedBounds withConfiguration(StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringCollectionPathReaderWithFixedBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<Collection<String>> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsStringCollectionWithFixedBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringCollectionPathReaderWithFixedBoundsConfiguration extends StringCollectionPathReaderConfiguration {
        private LimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration streamReaderConfig;

        public StringCollectionPathReaderWithFixedBoundsConfiguration withStreamReader(LimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration();
        }
    }
}
