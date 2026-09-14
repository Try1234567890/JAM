package com.github.trfiles.management.io.readers.unlimited.fromStream;

import com.github.utilities.options.IntegerOption;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedStreamReaderAsChars extends UnlimitedStreamReader<Collection<char[]>> {
    private StreamReaderAsCharsConfiguration configuration;

    private UnlimitedStreamReaderAsChars(InputStream input, StreamReaderAsCharsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsCharsConfiguration();
    }

    public static UnlimitedStreamReaderAsChars newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsChars(input, new StreamReaderAsCharsConfiguration());
    }

    public static UnlimitedStreamReaderAsChars newInstance(InputStream input, StreamReaderAsCharsConfiguration configuration) {
        return new UnlimitedStreamReaderAsChars(input, configuration);
    }

    public UnlimitedStreamReaderAsChars withConfiguration(StreamReaderAsCharsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StreamReaderAsCharsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<char[]> readEffectively(InputStream value, long offset, long length) throws Exception {
        Collection<byte[]> allBytes = UnlimitedStreamReaderAsBytes.newInstance(value, getConfiguration().getBytesReaderConfig())
                .read(offset, length);
        Collection<char[]> allChars = new LinkedList<>();

        int radix = getConfiguration().getRadix();
        for (byte[] bytes : allBytes) {
            char[] chars = new char[bytes.length];

            for (int i = 0; i < bytes.length; i++) {
                char cChar = Character.forDigit(bytes[i], radix);
                chars[i] = cChar;
            }

            allChars.add(chars);
        }

        return allChars;
    }


    public static class StreamReaderAsCharsConfiguration extends StreamReaderConfiguration<Collection<char[]>> {
        private final IntegerOption RADIX = new IntegerOption(10);
        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration bytesReaderConfig;

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

        public StreamReaderAsCharsConfiguration withBytesReader(UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.bytesReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getBytesReaderConfig() {
            return bytesReaderConfig != null ? bytesReaderConfig : new UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }
    }
}
