package com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollectionWithFixedBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsStringCollectionWithFixedBounds extends UnlimitedPathReaderAsStringCollection {
    private StringCollectionPathReaderWithFixedBoundsConfiguration configuration;

    private UnlimitedPathReaderAsStringCollectionWithFixedBounds(Path input, StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringCollectionPathReaderWithFixedBoundsConfiguration();
    }

    public static UnlimitedPathReaderAsStringCollectionWithFixedBounds newInstance(Path input) {
        return new UnlimitedPathReaderAsStringCollectionWithFixedBounds(input, new StringCollectionPathReaderWithFixedBoundsConfiguration());
    }

    public static UnlimitedPathReaderAsStringCollectionWithFixedBounds newInstance(Path input, StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        return new UnlimitedPathReaderAsStringCollectionWithFixedBounds(input, configuration);
    }

    public UnlimitedPathReaderAsStringCollectionWithFixedBounds withConfiguration(StringCollectionPathReaderWithFixedBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringCollectionPathReaderWithFixedBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<Collection<String>>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsStringCollectionWithFixedBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringCollectionPathReaderWithFixedBoundsConfiguration extends StringCollectionPathReaderConfiguration {
        private UnlimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration streamReaderConfig;

        public StringCollectionPathReaderWithFixedBoundsConfiguration withStreamReader(UnlimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsStringCollectionWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration();
        }
    }
}
