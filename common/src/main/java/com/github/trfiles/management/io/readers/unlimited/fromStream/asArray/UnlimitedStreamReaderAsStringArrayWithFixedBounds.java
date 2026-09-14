package com.github.trfiles.management.io.readers.unlimited.fromStream.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderAsString;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedStreamReaderAsStringArrayWithFixedBounds extends UnlimitedStreamReaderAsStringArray {
    private StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration;

    private UnlimitedStreamReaderAsStringArrayWithFixedBounds(InputStream input, StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
    }

    public static UnlimitedStreamReaderAsStringArrayWithFixedBounds newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsStringArrayWithFixedBounds(input, new StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration());
    }

    public static UnlimitedStreamReaderAsStringArrayWithFixedBounds newInstance(InputStream input, StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        return new UnlimitedStreamReaderAsStringArrayWithFixedBounds(input, configuration);
    }

    public UnlimitedStreamReaderAsStringArrayWithFixedBounds withConfiguration(StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<String[]> readEffectively(InputStream value, long offset, long length) throws Exception {
        checkIndexes(value, offset, length);

        Collection<String> allContent = UnlimitedStreamReaderAsString.newInstance(value, getConfiguration().getStringReaderConfig())
                .read(offset, length);
        String lineSeparator = getConfiguration().getLinesSeparator();

        Collection<String[]> allLines = new ArrayList<>();

        for (String content : allContent) {
            String[] line = content.split(lineSeparator);
            allLines.add(line);
        }

        return allLines;
    }

    public static class StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration extends StreamReaderAsStringArrayConfiguration {
        private UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration stringReaderConfig;

        public StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration withStringReader(UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.stringReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStringReaderConfig() {
            return stringReaderConfig != null ? stringReaderConfig : new UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }

    }
}
