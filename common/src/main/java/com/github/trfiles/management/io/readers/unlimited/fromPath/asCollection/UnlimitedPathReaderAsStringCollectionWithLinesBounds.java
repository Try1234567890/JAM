package com.github.trfiles.management.io.readers.unlimited.fromPath.asCollection;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection.UnlimitedStreamReaderAsStringCollectionWithLinesBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsStringCollectionWithLinesBounds extends UnlimitedPathReaderAsStringCollection {
    private StringCollectionPathReaderWithLinesBoundsConfiguration configuration;

    private UnlimitedPathReaderAsStringCollectionWithLinesBounds(Path input, StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringCollectionPathReaderWithLinesBoundsConfiguration();
    }

    public static UnlimitedPathReaderAsStringCollectionWithLinesBounds newInstance(Path input) {
        return new UnlimitedPathReaderAsStringCollectionWithLinesBounds(input, new StringCollectionPathReaderWithLinesBoundsConfiguration());
    }

    public static UnlimitedPathReaderAsStringCollectionWithLinesBounds newInstance(Path input, StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        return new UnlimitedPathReaderAsStringCollectionWithLinesBounds(input, configuration);
    }

    public UnlimitedPathReaderAsStringCollectionWithLinesBounds withConfiguration(StringCollectionPathReaderWithLinesBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringCollectionPathReaderWithLinesBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<Collection<String>>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsStringCollectionWithLinesBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringCollectionPathReaderWithLinesBoundsConfiguration extends StringCollectionPathReaderConfiguration {
        private UnlimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration streamReaderConfig;

        public StringCollectionPathReaderWithLinesBoundsConfiguration withStreamReader(UnlimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsStringCollectionWithLinesBounds.StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration();
        }
    }
}
