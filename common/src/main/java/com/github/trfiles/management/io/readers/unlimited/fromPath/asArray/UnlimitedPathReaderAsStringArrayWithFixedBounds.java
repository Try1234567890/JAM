package com.github.trfiles.management.io.readers.unlimited.fromPath.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithFixedBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsStringArrayWithFixedBounds extends UnlimitedPathReaderAsStringArray {
    private StringArrayPathReaderWithFixedBoundsConfiguration configuration;

    private UnlimitedPathReaderAsStringArrayWithFixedBounds(Path input, StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringArrayPathReaderWithFixedBoundsConfiguration();
    }

    public static UnlimitedPathReaderAsStringArrayWithFixedBounds newInstance(Path input) {
        return new UnlimitedPathReaderAsStringArrayWithFixedBounds(input, new StringArrayPathReaderWithFixedBoundsConfiguration());
    }

    public static UnlimitedPathReaderAsStringArrayWithFixedBounds newInstance(Path input, StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        return new UnlimitedPathReaderAsStringArrayWithFixedBounds(input, configuration);
    }

    public UnlimitedPathReaderAsStringArrayWithFixedBounds withConfiguration(StringArrayPathReaderWithFixedBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringArrayPathReaderWithFixedBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<String[]>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsStringArrayWithFixedBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringArrayPathReaderWithFixedBoundsConfiguration extends StringArrayPathReaderConfiguration {
        private UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration streamReaderConfig;

        public StringArrayPathReaderWithFixedBoundsConfiguration withStreamReader(UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsStringArrayWithFixedBounds.StreamReaderAsStringArrayWithFixedBoundsArrayConfiguration();
        }

    }
}
