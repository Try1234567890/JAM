package com.github.trfiles.management.io.readers.limited.fromStream.asCollection;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.utilities.options.Option;
import com.github.utilities.options.StringOption;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class LimitedStreamReaderAsStringCollection extends LimitedStreamReader<Collection<String>> {

    protected LimitedStreamReaderAsStringCollection(InputStream input) {
        super(input);
    }

    @Override
    public abstract StreamReaderAsStringCollectionConfiguration getConfiguration();

    public static class StreamReaderAsStringCollectionConfiguration extends StreamReaderConfiguration<Collection<String>> {
        private final Option<Charset> CHARSET = new Option<>(Charset.defaultCharset());
        private final StringOption LINES_SEPARATOR = new StringOption(System.lineSeparator());
        private final Option<Supplier<? extends Collection<String>>> COLLECTION_SUPPLIER = new Option<>(ArrayList::new);

        protected StreamReaderAsStringCollectionConfiguration() {
            SHOULD_CHECK_INDEXES.set(false);
        }

        public StreamReaderAsStringCollectionConfiguration withCharset(Charset charset) {
            CHARSET.set(charset);
            return this;
        }

        public StreamReaderAsStringCollectionConfiguration withLinesSeparator(String line) {
            LINES_SEPARATOR.set(line);
            return this;
        }

        private StreamReaderAsStringCollectionConfiguration asCollection(Supplier<? extends Collection<String>> supplier) {
            COLLECTION_SUPPLIER.set(supplier);
            return this;
        }

        protected Charset getCharset() {
            return Preconditions.simpleNotNull(CHARSET.get(), Charset.defaultCharset());
        }

        protected String getLinesSeparator() {
            return Preconditions.completeNotNull(LINES_SEPARATOR.get(), System.lineSeparator());
        }

        protected Collection<String> newCollection() {
            Supplier<? extends Collection<String>> supplier = COLLECTION_SUPPLIER.get();
            if (supplier == null) return new ArrayList<>();
            return supplier.get();
        }
    }
}
