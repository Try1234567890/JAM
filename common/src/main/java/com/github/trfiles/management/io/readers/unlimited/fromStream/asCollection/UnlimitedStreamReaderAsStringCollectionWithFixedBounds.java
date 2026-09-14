package com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithFixedBounds;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class UnlimitedStreamReaderAsStringCollectionWithFixedBounds extends UnlimitedStreamReader<Collection<Collection<String>>> {
    private StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration;

    private UnlimitedStreamReaderAsStringCollectionWithFixedBounds(InputStream input, StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration();
    }

    public static UnlimitedStreamReaderAsStringCollectionWithFixedBounds newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsStringCollectionWithFixedBounds(input, new StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration());
    }

    public static UnlimitedStreamReaderAsStringCollectionWithFixedBounds newInstance(InputStream input, StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        return new UnlimitedStreamReaderAsStringCollectionWithFixedBounds(input, configuration);
    }

    public UnlimitedStreamReaderAsStringCollectionWithFixedBounds withConfiguration(StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<Collection<String>> readEffectively(InputStream value, long offset, long length) throws Exception {
        Collection<String[]> allLines = UnlimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(value, getConfiguration().getArrayReaderConfig()).read(offset, length);

        Collection<Collection<String>> newLines = getConfiguration().newCollection();

        for (String[] lines : allLines) {
            newLines.add(Arrays.asList(lines));
        }

        return newLines;
    }

    public static class StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration extends UnlimitedStreamReaderAsStringCollection.StreamReaderAsStringCollectionConfiguration {
        private UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration arrayReaderConfig;


        public StreamReaderAsStringArrayWithFixedBoundsCollectionConfiguration withArrayReader(UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
            this.arrayReaderConfig = configuration;
            return this;
        }

        public UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getArrayReaderConfig() {
            return arrayReaderConfig != null ? arrayReaderConfig : new UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
        }

    }
}
