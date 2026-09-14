package com.github.trfiles.management.io.streamers.toPath;

import com.github.trfiles.management.managers.FileManager;
import com.github.trfiles.management.io.streamers.InstanceStreamer;
import com.github.trfiles.management.io.streamers.StreamerConfiguration;
import com.github.trfiles.management.io.streamers.toStream.StreamStreamer;
import com.github.trfiles.management.io.writers.toPath.PathWriter;
import com.github.trfiles.management.newPathPolicy.CreateFileAndDirectories;
import com.github.trfiles.management.newPathPolicy.CreateOnlyFile;
import com.github.trfiles.management.newPathPolicy.DoNothing;
import com.github.trfiles.management.newPathPolicy.NewPathPolicy;
import com.github.utilities.options.EnumOption;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.function.Function;

public abstract class PathStreamer<I> extends InstanceStreamer<I, Path> {

    protected PathStreamer(I source, Path destination) {
        super(source, destination);
    }

    @Override
    public abstract PathConfiguration<I> getConfiguration();

    @Override
    protected void streamEffectively(I source, Path destination, long offset, long length) throws Exception {
        getConfiguration().getNewPathPolicy().apply(destination).run();
        checkWritable(destination);

        try (OutputStream os = FileManager.newOutputStream(destination)) {
            toStreamStreamer(source, os).stream(offset, length);
        }
    }

    protected abstract StreamStreamer<I> toStreamStreamer(I source, OutputStream os);

    public abstract static class PathConfiguration<I> extends StreamerConfiguration<I, Path> {
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
