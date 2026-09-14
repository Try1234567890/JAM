package com.github.trfiles.management.io.readers.limited.fromPath.asArray;

import com.github.trfiles.management.io.readers.limited.fromStream.LimitedStreamReader;
import com.github.trfiles.management.io.readers.limited.fromStream.asArray.LimitedStreamReaderAsStringArrayWithLinesBounds;

import java.io.InputStream;
import java.nio.file.Path;

public class LimitedPathReaderAsStringArrayWithLinesBounds extends LimitedPathReaderAsStringArray {
    private StringArrayPathReaderWithLinesBoundsConfiguration configuration;

    private LimitedPathReaderAsStringArrayWithLinesBounds(Path input, StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new StringArrayPathReaderWithLinesBoundsConfiguration();
    }

    public static LimitedPathReaderAsStringArrayWithLinesBounds newInstance(Path input) {
        return new LimitedPathReaderAsStringArrayWithLinesBounds(input, new StringArrayPathReaderWithLinesBoundsConfiguration());
    }

    public static LimitedPathReaderAsStringArrayWithLinesBounds newInstance(Path input, StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        return new LimitedPathReaderAsStringArrayWithLinesBounds(input, configuration);
    }

    public LimitedPathReaderAsStringArrayWithLinesBounds withConfiguration(StringArrayPathReaderWithLinesBoundsConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public StringArrayPathReaderWithLinesBoundsConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected LimitedStreamReader<String[]> toStreamReader(InputStream is) {
        return LimitedStreamReaderAsStringArrayWithLinesBounds.newInstance(is, getConfiguration().getStreamReaderConfig());
    }

    public static class StringArrayPathReaderWithLinesBoundsConfiguration extends StringArrayPathReaderConfiguration {
        private LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration streamReaderConfig;

        public StringArrayPathReaderWithLinesBoundsConfiguration withStreamReader(LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration configuration) {
            this.streamReaderConfig = configuration;
            return this;
        }

        private LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration getStreamReaderConfig() {
            return streamReaderConfig != null ? streamReaderConfig : new LimitedStreamReaderAsStringArrayWithLinesBounds.StreamReaderAsStringArrayWithLinesBoundsArrayConfiguration();
        }
    }
}
