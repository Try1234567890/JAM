package com.github.trfiles.management.io.readers.unlimited.fromPath.asArray;

import com.github.trfiles.management.io.readers.unlimited.fromStream.UnlimitedStreamReader;
import com.github.trfiles.management.io.readers.unlimited.fromStream.asArray.UnlimitedStreamReaderAsStringArrayWithLinesBounds;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

public class UnlimitedPathReaderAsStringArrayWithLinesBounds extends UnlimitedPathReaderAsStringArray {
    private StringArrayPathReaderWithLinesBoundsConfiguration configuration;

    private UnlimitedPathReaderAsStringArrayWithLinesBounds(Path input, StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringArrayPathReaderWithLinesBoundsConfiguration();
    }

    public static UnlimitedPathReaderAsStringArrayWithLinesBounds newInstance(Path input) {
        return new UnlimitedPathReaderAsStringArrayWithLinesBounds(input, new StringArrayPathReaderWithLinesBoundsConfiguration());
    }

    public static UnlimitedPathReaderAsStringArrayWithLinesBounds newInstance(Path input, StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        return new UnlimitedPathReaderAsStringArrayWithLinesBounds(input, configuration);
    }

    public UnlimitedPathReaderAsStringArrayWithLinesBounds withConfiguration(StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringArrayPathReaderWithLinesBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected UnlimitedStreamReader<Collection<String[]>> toStreamReader(InputStream is) {
        return UnlimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringArrayPathReaderWithLinesBoundsConfiguration extends StringArrayPathReaderConfiguration {
        private UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration streamReaderConfig;

        public StringArrayPathReaderWithLinesBoundsConfiguration withStreamReader(UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new UnlimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
        }
    }
}
