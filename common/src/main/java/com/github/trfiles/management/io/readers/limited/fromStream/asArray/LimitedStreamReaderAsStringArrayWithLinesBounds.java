package com.github.trfiles.management.io.readers.limited.fromStream.asArray;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderAsString;
import com.github.utilities.options.EnumOption;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;

public class LimitedStreamReaderAsStringArrayWithLinesBounds extends LimitedStreamReaderAsStringArray {
    private StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration;

    private LimitedStreamReaderAsStringArrayWithLinesBounds(InputStream input, StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
    }

    public static LimitedStreamReaderAsStringArrayWithLinesBounds newInstance(InputStream input) {
        return new LimitedStreamReaderAsStringArrayWithLinesBounds(input, new StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration());
    }

    public static LimitedStreamReaderAsStringArrayWithLinesBounds newInstance(InputStream input, StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        return new LimitedStreamReaderAsStringArrayWithLinesBounds(input, configuration);
    }

    public LimitedStreamReaderAsStringArrayWithLinesBounds withConfiguration(StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected String[] readEffectively(InputStream value, long offset, long length) throws Exception {
        if (offset < 0) {
            throw new IndexOutOfBoundsException("The \"offset\" index is less than 0!");
        }

        if (offset > length) {
            throw new IndexOutOfBoundsException("The \"length\" index is greater than the size of the value!");
        }

        String content = LimitedStreamReaderAsString.newInstance(value, getConfiguration().getStringReaderConfig()).read();
        String lineSeparator = getConfiguration().getLinesSeparator();
        String[] lines = content.split(lineSeparator);
        if (lines.length > length) {
            return onOversize(lines, offset, length);
        } else {
            return lines;
        }
    }

    private String[] onOversize(String[] value, long offset, long length) {
        return switch (getConfiguration().getOversizeCase()) {
            case RESIZE -> resize(value, (length - offset));
            case EXCEPTION -> throw new IndexOutOfBoundsException("The lines read are more than the given length.");
            case RETURN_AS_IS -> value;
        };
    }

    private String[] resize(String[] value, long size) {
        if (size > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Cannot read " + size + " lines from destination. Max lines that can be read are " + Integer.MAX_VALUE +
                    ". Use streamings instead for bigger size.");
        }
        String[] resized = new String[(int) size];
        System.arraycopy(value, 0, resized, 0, resized.length);
        return resized;
    }

    public static class StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration extends StreamReaderAsStringArrayConfiguration {
        private final EnumOption<OversizeCase> ON_OVERSIZE = new EnumOption<>(OversizeCase.EXCEPTION);
        private LimitedStreamReaderAsString.StreamReaderAsStringConfiguration stringReaderConfig;

        public StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration onOversize(OversizeCase sizeCase) {
            ON_OVERSIZE.set(sizeCase);
            return this;
        }

        private OversizeCase getOversizeCase() {
            return Preconditions.simpleNotNull(ON_OVERSIZE.get(), OversizeCase.EXCEPTION);
        }

        public StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration withStringReader(LimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.stringReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStringReaderConfig() {
            return stringReaderConfig != null ? stringReaderConfig : new LimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }
    }

    public enum OversizeCase {
        EXCEPTION,
        RESIZE,
        RETURN_AS_IS
    }
}
