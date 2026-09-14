package com.github.trfiles.management.io.readers.limited.fromPath;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReaderAsBytes;

import java.io.InputStream;
import java.nio.file.Path;

public class LimitedPathReaderAsBytes extends LimitedPathReader<byte[]> {
    private BytesPathReaderConfiguration configuration;

    private LimitedPathReaderAsBytes(Path input, BytesPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new BytesPathReaderConfiguration();
    }

    public static LimitedPathReaderAsBytes newInstance(Path input) {
        return new LimitedPathReaderAsBytes(input, new BytesPathReaderConfiguration());
    }

    public static LimitedPathReaderAsBytes newInstance(Path input, BytesPathReaderConfiguration configuration) {
        return new LimitedPathReaderAsBytes(input, configuration);
    }

    public LimitedPathReaderAsBytes withConfiguration(BytesPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public BytesPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<byte[]> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsBytes.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class BytesPathReaderConfiguration extends PathReaderConfiguration<byte[]> {
        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration streamReaderConfig;

        public BytesPathReaderConfiguration withStreamReader(LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }
    }
}
