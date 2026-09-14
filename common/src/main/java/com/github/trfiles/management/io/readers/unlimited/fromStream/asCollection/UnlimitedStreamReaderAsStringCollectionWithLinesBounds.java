package com.github.trfiles.management.io.readers.unlimited.fromStream.asCollection;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithFixedBounds;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithLinesBounds;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class UnlimitedStreamReaderAsStringCollectionWithLinesBounds extends UnlimitedStreamReader<Collection<Collection<String>>> {
    private StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration;

    private UnlimitedStreamReaderAsStringCollectionWithLinesBounds(InputStream input, StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration();
    }

    public static UnlimitedStreamReaderAsStringCollectionWithLinesBounds newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsStringCollectionWithLinesBounds(input, new StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration());
    }

    public static UnlimitedStreamReaderAsStringCollectionWithLinesBounds newInstance(InputStream input, StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        return new UnlimitedStreamReaderAsStringCollectionWithLinesBounds(input, configuration);
    }

    public UnlimitedStreamReaderAsStringCollectionWithLinesBounds withConfiguration(StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    @Override
    public StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<Collection<String>> readEffectively(InputStream value, long offset, long length) throws Exception {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }

        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the value!");
        }

        Collection<String[]> allLines = UnlimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(value, getConfiguration().getArrayReaderConfig()).read();
        Collection<Collection<String>> newLines = getConfiguration().newCollection();

        long index = 0;
        for (String[] lines : allLines) {
            if (index < offset) {
                index++;
                continue;
            }
            if (index >= length) break;
            newLines.add(Arrays.asList(lines));
            index++;
        }

        return newLines;
    }

    public static class StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration extends UnlimitedStreamReaderAsStringCollection.StreamReaderAsStringCollectionConfiguration {
        private UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration arrayReaderConfig;

        public StreamReaderAsStringCollectionWithLinesBoundsCollectionConfiguration withArrayReader(UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
            this.arrayReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getArrayReaderConfig() {
            return arrayReaderConfig != null ? arrayReaderConfig : new UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
        }

    }

}
