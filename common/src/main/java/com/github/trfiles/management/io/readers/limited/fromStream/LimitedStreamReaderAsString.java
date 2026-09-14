package com.github.trfiles.management.io.readers.limited.fromStream;

import com.github.utilities.options.Option;
import com.github.utilities.validators.Preconditions;

import java.io.InputStream;
import java.nio.charset.Charset;

public class LimitedStreamReaderAsString extends LimitedStreamReader<String> {
    private StreamReaderAsStringConfiguration configuration;

    private LimitedStreamReaderAsString(InputStream input, StreamReaderAsStringConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StreamReaderAsStringConfiguration();
    }

    public static LimitedStreamReaderAsString newInstance(InputStream input) {
        return new LimitedStreamReaderAsString(input, new StreamReaderAsStringConfiguration());
    }

    public static LimitedStreamReaderAsString newInstance(InputStream input, StreamReaderAsStringConfiguration configuration) {
        return new LimitedStreamReaderAsString(input, configuration);
    }

    public LimitedStreamReaderAsString withConfiguration(StreamReaderAsStringConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StreamReaderAsStringConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected String readEffectively(InputStream value, long offset, long length) throws Exception {
        byte[] bytes = LimitedStreamReaderAsBytes.newInstance(value, getConfiguration().getBytesReaderConfig())
                .read(offset, length);
        return new String(bytes, getConfiguration().getCharset());
    }


    public static class StreamReaderAsStringConfiguration extends StreamReaderConfiguration<String> {
        private final Option<Charset> CHARSET = new Option<>(Charset.defaultCharset());
        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration bytesReaderConfig;

        public StreamReaderAsStringConfiguration withCharset(Charset charset) {
            CHARSET.set(charset);
            return this;
        }

        public Charset getCharset() {
            return Preconditions.simpleNotNull(CHARSET.get(), Charset.defaultCharset());
        }

        public StreamReaderAsStringConfiguration withBytesReader(LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.bytesReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getBytesReaderConfig() {
            return bytesReaderConfig != null ? bytesReaderConfig : new LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }

    }
}
