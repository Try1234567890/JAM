package com.github.trfiles.management.io.readers.limited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.asCollection.LimitedStreamReaderAsStringCollectionWithLinesBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class LimitedPathReaderAsStringCollectionWithLinesBounds extends LimitedPathReaderAsStringCollection {
    private StringCollectionPathReaderWithLinesBoundsConfiguration configuration;

    private LimitedPathReaderAsStringCollectionWithLinesBounds(Path input, StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringCollectionPathReaderWithLinesBoundsConfiguration();
    }

    public static LimitedPathReaderAsStringCollectionWithLinesBounds newInstance(Path input) {
        return new LimitedPathReaderAsStringCollectionWithLinesBounds(input, new StringCollectionPathReaderWithLinesBoundsConfiguration());
    }

    public static LimitedPathReaderAsStringCollectionWithLinesBounds newInstance(Path input, StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        return new LimitedPathReaderAsStringCollectionWithLinesBounds(input, configuration);
    }

    public LimitedPathReaderAsStringCollectionWithLinesBounds withConfiguration(StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringCollectionPathReaderWithLinesBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<Collection<String>> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsStringCollectionWithLinesBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringCollectionPathReaderWithLinesBoundsConfiguration extends StringCollectionPathReaderConfiguration {
        private LimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration streamReaderConfig;

        public StringCollectionPathReaderWithLinesBoundsConfiguration withStreamReader(LimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration();
        }
    }
}
