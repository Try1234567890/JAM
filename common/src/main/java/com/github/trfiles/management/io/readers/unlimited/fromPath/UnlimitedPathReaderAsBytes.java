package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderAsBytes;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsBytes extends UnlimitedPathReader<Collection<byte[]>> {
    private BytesPathReaderConfiguration configuration;

    private UnlimitedPathReaderAsBytes(Path input, BytesPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new BytesPathReaderConfiguration();
    }

    public static UnlimitedPathReaderAsBytes newInstance(Path input) {
        return new UnlimitedPathReaderAsBytes(input, new BytesPathReaderConfiguration());
    }

    public static UnlimitedPathReaderAsBytes newInstance(Path input, BytesPathReaderConfiguration configuration) {
        return new UnlimitedPathReaderAsBytes(input, configuration);
    }

    public UnlimitedPathReaderAsBytes withConfiguration(BytesPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public BytesPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<byte[]>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsBytes.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class BytesPathReaderConfiguration extends PathReaderConfiguration<Collection<byte[]>> {
        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration streamReaderConfig;

        public BytesPathReaderConfiguration withStreamReader(UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsBytes.StreamReaderAsBytesConfiguration();
        }
    }
}
