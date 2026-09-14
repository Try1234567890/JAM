package com.github.trfiles.management.io.readers.limited.fromPath;

import com.github.trfiles.management.io.readers.InstanceReader;
import com.github.trfiles.management.io.readers.ReaderConfiguration;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LimitedKernelPathReader extends InstanceReader<Path, ByteBuffer> {
    private KernelPathReaderConfiguration configuration;

    protected LimitedKernelPathReader(Path input, KernelPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new KernelPathReaderConfiguration();
    }

    public static LimitedKernelPathReader newInstance(Path input) {
        return new LimitedKernelPathReader(input, new KernelPathReaderConfiguration());
    }

    private static LimitedKernelPathReader newInstance(Path input, KernelPathReaderConfiguration configuration) {
        return new LimitedKernelPathReader(input, configuration);
    }

    @Override
    public KernelPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    public LimitedKernelPathReader withConfiguration(KernelPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected ByteBuffer readEffectively(Path value, long offset, long length) throws Exception {
        checkReadable(value);

        try (FileChannel channel = FileChannel.open(value, StandardOpenOption.READ)) {
            return channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        }
    }

    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class KernelPathReaderConfiguration extends ReaderConfiguration<Path, ByteBuffer> {

    }
}
