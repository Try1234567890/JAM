package com.github.trfiles.management.io.readers.unlimited.fromStream.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderAsString;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedStreamReaderAsStringArrayWithLinesBounds extends UnlimitedStreamReaderAsStringArray {
    private StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration;

    private UnlimitedStreamReaderAsStringArrayWithLinesBounds(InputStream input, StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
    }

    public static UnlimitedStreamReaderAsStringArrayWithLinesBounds newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsStringArrayWithLinesBounds(input, new StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration());
    }

    public static UnlimitedStreamReaderAsStringArrayWithLinesBounds newInstance(InputStream input, StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        return new UnlimitedStreamReaderAsStringArrayWithLinesBounds(input, configuration);
    }

    public UnlimitedStreamReaderAsStringArrayWithLinesBounds withConfiguration(StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }


    @Override
    public StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<String[]> readEffectively(InputStream value, long offset, long length) throws Exception {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }

        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the value!");
        }

        Collection<String> allContent = UnlimitedStreamReaderAsString.newInstance(value, getConfiguration().getStringReaderConfig()).read();
        String lineSeparator = getConfiguration().getLinesSeparator();

        Collection<String[]> allLines = new ArrayList<>();
        long index = 0;
        for (String content : allContent) {
            if (index < offset) {
                index++;
                continue;
            }
            if (index >= length) break;
            String[] line = content.split(lineSeparator);
            allLines.add(line);
            index++;
        }

        return allLines;
    }


    public static class StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration extends StreamReaderAsStringArrayConfiguration {
        private UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration stringReaderConfig;

        public StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration withStringReader(UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.stringReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStringReaderConfig() {
            return stringReaderConfig != null ? stringReaderConfig : new UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }
    }
}
