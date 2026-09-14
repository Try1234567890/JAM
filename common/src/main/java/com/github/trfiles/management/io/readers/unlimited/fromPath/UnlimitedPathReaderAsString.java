package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReaderAsString;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsString extends UnlimitedPathReader<Collection<String>> {
    private StringPathReaderConfiguration configuration;

    private UnlimitedPathReaderAsString(Path input, StringPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringPathReaderConfiguration();
    }

    public static UnlimitedPathReaderAsString newInstance(Path input) {
        return new UnlimitedPathReaderAsString(input, new StringPathReaderConfiguration());
    }

    public static UnlimitedPathReaderAsString newInstance(Path input, StringPathReaderConfiguration configuration) {
        return new UnlimitedPathReaderAsString(input, configuration);
    }

    public UnlimitedPathReaderAsString withConfiguration(StringPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<String>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsString.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringPathReaderConfiguration extends PathReaderConfiguration<Collection<String>> {
        private UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration streamReaderConfig;

        public StringPathReaderConfiguration withStreamReader(UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        public UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsString.StreamReaderAsStringConfiguration();
        }
    }
}
