package com.github.trfiles.management.io.readers.unlimited.fromPath;

import com.github.trfiles.management.io.readers.InstanceReader;
import com.github.trfiles.management.io.readers.ReaderConfiguration;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class UnlimitedKernelPathReader extends InstanceReader<Path, Collection<ByteBuffer>> {
    private KernelPathReaderConfiguration configuration;

    protected UnlimitedKernelPathReader(Path input, KernelPathReaderConfiguration configuration) {
        super(input);
        this.configuration = configuration != null ? configuration : new KernelPathReaderConfiguration();
    }

    public static UnlimitedKernelPathReader newInstance(Path input) {
        return new UnlimitedKernelPathReader(input, new KernelPathReaderConfiguration());
    }

    private static UnlimitedKernelPathReader newInstance(Path input, KernelPathReaderConfiguration configuration) {
        return new UnlimitedKernelPathReader(input, configuration);
    }

    @Override
    public KernelPathReaderConfiguration getConfiguration() {
        return configuration;
    }

    public UnlimitedKernelPathReader withConfiguration(KernelPathReaderConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    protected Collection<ByteBuffer> readEffectively(Path value, long offset, long length) throws Exception {
        checkReadable(value);

        long size = length - offset;
        int maxChunkSize = Integer.MAX_VALUE - 8;

        long fullBytesBufferAmt = size / maxChunkSize;
        int reminder = (int) (size % maxChunkSize);

        Collection<ByteBuffer> buffers = new ArrayList<>();

        try (FileChannel channel = FileChannel.open(value, StandardOpenOption.READ)) {
            long currOffset = offset;

            for (long i = 0; i < fullBytesBufferAmt; i++) {
                ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, currOffset, maxChunkSize);
                buffers.add(buffer);
                currOffset += maxChunkSize;
            }

            if (reminder > 0) {
                ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, currOffset, reminder);
                buffers.add(buffer);
            }
        }

        return buffers;
    }


    @Override
    protected long _size(Path value) throws Exception {
        return Files.size(value);
    }

    public static class KernelPathReaderConfiguration extends ReaderConfiguration<Path, Collection<ByteBuffer>> {

    }
}
