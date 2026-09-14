package com.github.trfiles.management.io.readers.limited.fromStream;

import com.github.utilities.options.IntegerOption;

import java.io.InputStream;

public class LimitedStreamReaderAsChars extends LimitedStreamReader<char[]> {
    private StreamReaderAsCharsConfiguration configuration;

    private LimitedStreamReaderAsChars(InputStream input, StreamReaderAsCharsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsCharsConfiguration();
    }

    public static LimitedStreamReaderAsChars newInstance(InputStream input) {
        return new LimitedStreamReaderAsChars(input, new StreamReaderAsCharsConfiguration());
    }

    public static LimitedStreamReaderAsChars newInstance(InputStream input, StreamReaderAsCharsConfiguration configuration) {
        return new LimitedStreamReaderAsChars(input, configuration);
    }

    public LimitedStreamReaderAsChars withConfiguration(StreamReaderAsCharsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StreamReaderAsCharsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected char[] readEffectively(InputStream value, long offset, long length) throws Exception {
        byte[] bytes = LimitedStreamReaderAsBytes.newInstance(value, getConfiguration().getBytesReaderConfig())
                .read(offset, length);
        char[] chars = new char[bytes.length];

        int radix = getConfiguration().getRadix();
        for (int i = 0; i < bytes.length; i++) {
            char cChar = Character.forDigit(bytes[i], radix);
            chars[i] = cChar;
        }

        return chars;
    }


    public static class StreamReaderAsCharsConfiguration extends StreamReaderConfiguration<char[]> {
        private final IntegerOption RADIX = new IntegerOption(10);
        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration bytesReaderConfig;

        public StreamReaderAsCharsConfiguration withRadix(int radix) {
            RADIX.set(radix);
            return this;
        }

        public int getRadix() {
            int radix = RADIX.get();
            if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
                throw new IndexOutOfBoundsException("The radix " + radix + " is out of bounds. The bounds of valid radixes are: " +
                        Character.MIN_RADIX + " - " + Character.MAX_RADIX);
            }
            return radix;
        }

        public StreamReaderAsCharsConfiguration withBytesReader(LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.bytesReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getBytesReaderConfig() {
            return bytesReaderConfig != null ? bytesReaderConfig : new LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }
    }
}
