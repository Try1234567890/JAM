package com.github.trfiles.management.io.readers.unlimited.fromStream.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.utilities.options.Option;
import com.github.utilities.options.StringOption;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;

public abstract class UnlimitedStreamReaderAsStringArray extends UnlimitedStreamReader<Collection<String[]>> {

    protected UnlimitedStreamReaderAsStringArray(InputStream input) {
        super(input);
    }

    @Override
    public abstract StreamReaderAsStringArrayConfiguration getConfiguration();

    public static class StreamReaderAsStringArrayConfiguration extends StreamReaderConfiguration<Collection<String[]>> {
        private final Option<Charset> CHARSET = new Option<>(Charset.defaultCharset());
        private final StringOption LINES_SEPARATOR = new StringOption(System.lineSeparator());

        protected StreamReaderAsStringArrayConfiguration() {
            SHOULD_CHECK_INDEXES.set(false);
        }

        public StreamReaderAsStringArrayConfiguration withCharset(Charset charset) {
            CHARSET.set(charset);
            return this;
        }

        public StreamReaderAsStringArrayConfiguration withLinesSeparator(String line) {
            LINES_SEPARATOR.set(line);
            return this;
        }

        public Charset getCharset() {
            return Preconditions.simpleNotNull(CHARSET.get(), Charset.defaultCharset());
        }

        public String getLinesSeparator() {
            return Preconditions.completeNotNull(LINES_SEPARATOR.get(), System.lineSeparator());
        }
    }
}
