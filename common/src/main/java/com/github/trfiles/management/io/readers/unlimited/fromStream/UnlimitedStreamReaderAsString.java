package com.github.trfiles.management.io.readers.unlimited.fromStream;

import com.github.utilities.options.Option;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedStreamReaderAsString extends UnlimitedStreamReader<Collection<String>> {
    private StreamReaderAsStringConfiguration configuration;

    private UnlimitedStreamReaderAsString(InputStream input, StreamReaderAsStringConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringConfiguration();
    }

    public static UnlimitedStreamReaderAsString newInstance(InputStream input) {
        return new UnlimitedStreamReaderAsString(input, new StreamReaderAsStringConfiguration());
    }

    public static UnlimitedStreamReaderAsString newInstance(InputStream input, StreamReaderAsStringConfiguration configuration) {
        return new UnlimitedStreamReaderAsString(input, configuration);
    }

    public UnlimitedStreamReaderAsString withConfiguration(StreamReaderAsStringConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected Collection<String> readEffectively(InputStream value, long offset, long length) throws Exception {
        Collection<byte[]> allBytes = UnlimitedStreamReaderAsBytes.newInstance(value, getConfiguration().getBytesReaderConfig())
                .read(offset, length);
        Collection<String> strings = new LinkedList<>();
        for (byte[] bytes : allBytes) {
            String str = new String(bytes, getConfiguration().getCharset());
            strings.add(str);
        }

        return strings;
    }


    public static class StreamReaderAsStringConfiguration extends StreamReaderConfiguration<Collection<String>> {
        private final Option<Charset> CHARSET = new Option<>(Charset.defaultCharset());
        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration bytesReaderConfig;

        public StreamReaderAsStringConfiguration withCharset(Charset charset) {
            CHARSET.set(charset);
            return this;
        }

        public Charset getCharset() {
            return Preconditions.simpleNotNull(CHARSET.get(), Charset.defaultCharset());
        }

        public StreamReaderAsStringConfiguration withBytesReader(UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.bytesReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getBytesReaderConfig() {
            return bytesReaderConfig != null ? bytesReaderConfig : new UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }

    }
}
