package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.trfiles.management.io.writers.toStream.StringCollectionStreamWriter;
import com.github.utilities.options.Option;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.function.BiFunction;

public class StringCollectionPathWriter extends PathWriter<Collection<String>> {
    private StringCollectionPathWriterConfiguration configuration;

    private StringCollectionPathWriter(Collection<String> value, Path destination, StringCollectionPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringCollectionPathWriterConfiguration();
    }

    public static StringCollectionPathWriter newInstance(Collection<String> value, Path destination) {
        return new StringCollectionPathWriter(value, destination, new StringCollectionPathWriterConfiguration());
    }

    public static StringCollectionPathWriter newInstance(Collection<String> value, Path destination, StringCollectionPathWriterConfiguration configuration) {
        return new StringCollectionPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<Collection<String>> toStreamWriter(Collection<String> value, OutputStream os) {
        return StringCollectionStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    public StringCollectionPathWriter withConfiguration(StringCollectionPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StringCollectionPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(Collection<String> value) throws Exception {
        return value.size();
    }

    public static class StringCollectionPathWriterConfiguration extends PathConfiguration<Collection<String>> {
        private final Option<BiFunction<String, Path, StringPathWriter>> STRING_WRITER = new Option<>(StringPathWriter::newInstance);
        private StringCollectionStreamWriter.StringArrayStreamWriterConfiguration streamWriterConfig = new StringCollectionStreamWriter.StringArrayStreamWriterConfiguration();

        public StringCollectionPathWriterConfiguration withStringWriter(BiFunction<String, Path, StringPathWriter> creator) {
            STRING_WRITER.set(creator);
            return this;
        }

        private StringPathWriter getWriter(String str, Path out) {
            return Preconditions.simpleNotNull(STRING_WRITER.get(), StringPathWriter::newInstance).apply(str, out);
        }

        public StringCollectionPathWriterConfiguration withStreamWriter(StringCollectionStreamWriter.StringArrayStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public StringCollectionStreamWriter.StringArrayStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, StringCollectionStreamWriter.StringArrayStreamWriterConfiguration::new);
        }
    }
}
