package com.github.trfiles.management.io.downloaders.toPath;

import com.github.trfiles.management.io.downloaders.DownloaderConfiguration;
import com.github.trfiles.management.io.downloaders.InstanceDownloader;
import com.github.trfiles.management.io.downloaders.toStream.StreamDownloader;
import com.github.trfiles.management.managers.FileManager;
import com.github.trfiles.management.newPathPolicy.CreateFileAndDirectories;
import com.github.trfiles.management.newPathPolicy.CreateOnlyFile;
import com.github.trfiles.management.newPathPolicy.DoNothing;
import com.github.trfiles.management.newPathPolicy.NewPathPolicy;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Base class for downloaders whose destination is a file on disk, identified by a {@link Path}. Mirrors
 * {@code PathStreamer}: applies the configured {@link NewPathPolicy} to {@code destination}, opens it as an
 * {@link OutputStream}, and delegates the actual download to the corresponding {@code toStream} downloader.
 *
 * @param <I> the source of the process (a {@link java.net.URI} in every implementation currently in this
 *            package).
 */
public abstract class PathDownloader<I> extends InstanceDownloader<I, Path> {

    protected PathDownloader(I source, Path destination) {
        super(source, destination);
    }

    @Override
    public abstract PathConfiguration<I> getConfiguration();

    @Override
    protected void downloadEffectively(I source, Path destination, long offset, long length) throws Exception {
        getConfiguration().getNewPathPolicy().apply(destination).run();
        checkWritable(destination);

        try (OutputStream os = FileManager.newOutputStream(destination)) {
            toStreamDownloader(source, os).download(offset, length);
        }
    }

    protected abstract StreamDownloader<I> toStreamDownloader(I source, OutputStream os);

    public abstract static class PathConfiguration<I> extends DownloaderConfiguration<I, Path> {
        private Function<Path, ? extends NewPathPolicy> newPathPolicy = CreateFileAndDirectories::new;

        public PathConfiguration<I> withNewFilePolicy(Function<Path, ? extends NewPathPolicy> newPathPolicy) {
            this.newPathPolicy = newPathPolicy;
            return this;
        }

        public PathConfiguration<I> withCreateFilePolicy() {
            return withNewFilePolicy(CreateOnlyFile::new);
        }

        public PathConfiguration<I> withDoNothingPolicy() {
            return withNewFilePolicy(_ -> DoNothing.get());
        }

        public Function<Path, ? extends NewPathPolicy> getNewPathPolicy() {
            return newPathPolicy != null ? newPathPolicy : CreateFileAndDirectories::new;
        }
    }
}
