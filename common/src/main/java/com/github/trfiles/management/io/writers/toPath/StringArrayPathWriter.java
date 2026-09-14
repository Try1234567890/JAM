package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.trfiles.management.io.writers.toStream.StringArrayStreamWriter;
import com.github.utilities.options.Option;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.function.BiFunction;

public class StringArrayPathWriter extends PathWriter<String[]> {
    private StringArrayPathWriterConfiguration configuration;

    private StringArrayPathWriter(String[] value, Path destination, StringArrayPathWriterConfiguration configuration) {
        super(value, destination);
        this.configuration = configuration != null ? configuration : new StringArrayPathWriterConfiguration();
    }

    public static StringArrayPathWriter newInstance(String[] value, Path destination) {
        return new StringArrayPathWriter(value, destination, new StringArrayPathWriterConfiguration());
    }

    public static StringArrayPathWriter newInstance(String[] value, Path destination, StringArrayPathWriterConfiguration configuration) {
        return new StringArrayPathWriter(value, destination, configuration);
    }

    @Override
    protected StreamWriter<String[]> toStreamWriter(String[] value, OutputStream os) {
        return StringArrayStreamWriter.newInstance(value, os, getConfiguration().getStreamWriterConfig());
    }

    public StringArrayPathWriter withConfiguration(StringArrayPathWriterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }



    @Override
    public StringArrayPathWriterConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected long _size(String[] value) throws Exception {
        return value.length;
    }

    public static class StringArrayPathWriterConfiguration extends PathConfiguration<String[]> {
        private final Option<BiFunction<String, Path, StringPathWriter>> STRING_WRITER = new Option<>(StringPathWriter::newInstance);
        private StringArrayStreamWriter.StringCollectionStreamWriterConfiguration streamWriterConfig = new StringArrayStreamWriter.StringCollectionStreamWriterConfiguration();

        public StringArrayPathWriterConfiguration withStringWriter(BiFunction<String, Path, StringPathWriter> creator) {
            STRING_WRITER.set(creator);
            return this;
        }

        private StringPathWriter getWriter(String str, Path out) {
            return Preconditions.simpleNotNull(STRING_WRITER.get(), StringPathWriter::newInstance).apply(str, out);
        }

        public StringArrayPathWriterConfiguration withStreamWriter(StringArrayStreamWriter.StringCollectionStreamWriterConfiguration configuration) {
            this.streamWriterConfig = configuration;
            return this;
        }

        public StringArrayStreamWriter.StringCollectionStreamWriterConfiguration getStreamWriterConfig() {
            return Preconditions.simpleNotNullOtherwise(streamWriterConfig, StringArrayStreamWriter.StringCollectionStreamWriterConfiguration::new);
        }
    }
}
