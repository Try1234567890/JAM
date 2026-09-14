package com.github.trfiles.management.io.writers.toStream;

import com.github.utilities.options.IntegerOption;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;

public class CharsStreamWriter extends StreamWriter<char[]> {
    private BytesStreamWriterConfiguration configuration;

    private CharsStreamWriter(char[] value, OutputStream destination, BytesStreamWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new BytesStreamWriterConfiguration();
    }

    public static CharsStreamWriter newInstance(char[] value, OutputStream destination) {
        return new CharsStreamWriter(value, destination, new BytesStreamWriterConfiguration());
    }

    public static CharsStreamWriter newInstance(char[] value, OutputStream destination, BytesStreamWriterConfiguration configuration) {
        return new CharsStreamWriter(value, destination, configuration);
    }

    @Override
    protected void writeStream(char[] value, OutputStream destination, long offset, long length) throws Exception {
        char[] newValue = prepare(value, offset, length);
        int radix = configuration.getRadix();

        for (char cChar : newValue) {
            int cByte = Character.digit(cChar, radix);
            destination.write(cByte);
        }

    }


    private char[] prepare(char[] value, long offset, long length) throws IndexOutOfBoundsException {
        long size = length - offset;

        if (size > Integer.MAX_VALUE) {
            // sanity check. impossible java array size is limited to int max value.
            throw new IndexOutOfBoundsException("Cannot write " + size + " bytes to destination. Use streamings instead.");
        }

        char[] newValue = new char[(int) size];
        System.arraycopy(value, (int) offset, newValue, 0, (int) size);
        return newValue;
    }

    @Override
    protected long _size(char[] value) {
        return value.length;
    }

    public CharsStreamWriter withConfiguration(BytesStreamWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public BytesStreamWriterConfiguration getConfiguration() {
        return configuration;
    }

    public static class BytesStreamWriterConfiguration extends StreamConfiguration<char[]> {
        private final IntegerOption RADIX = new IntegerOption(10);

        public BytesStreamWriterConfiguration withRadix(int newValue) {
            RADIX.set(Preconditions.simpleParameterNotNull(newValue, "newValue"));
            return this;
        }

        public int getRadix() {
            int radix = RADIX.get();
            if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
                throw new IndexOutOfBoundsException("The radix " + radix + " is out of valid radixes bounds. Radix must be between: " + Character.MIN_RADIX + " - " + Character.MAX_RADIX);
            }
            return radix;
        }
    }
}
