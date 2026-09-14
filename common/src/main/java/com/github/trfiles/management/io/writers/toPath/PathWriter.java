package com.github.trfiles.management.io.writers.toPath;

import com.github.trfiles.management.managers.FileManager;
import com.github.trfiles.management.io.writers.InstanceWriter;
import com.github.trfiles.management.io.writers.WriterConfiguration;
import com.github.trfiles.management.io.writers.toStream.StreamWriter;
import com.github.trfiles.management.newPathPolicy.CreateFileAndDirectories;
import com.github.trfiles.management.newPathPolicy.CreateOnlyFile;
import com.github.trfiles.management.newPathPolicy.DoNothing;
import com.github.trfiles.management.newPathPolicy.NewPathPolicy;
import com.github.utilities.options.EnumOption;
import com.github.utilities.validators.Preconditions;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.function.Function;

public abstract class PathWriter<V> extends InstanceWriter<V, Path> {

    protected PathWriter(V value, Path destination) {
        super(value, destination);
    }


    @Override
    public abstract PathConfiguration<V> getConfiguration();

    @Override
    protected void writeEffectively(V value, Path destination, long offset, long length) throws Exception {
        getConfiguration().getNewPathPolicy().apply(destination).run();
        checkWritable(destination);

        try (OutputStream os = FileManager.newOutputStream(destination)) {
            toStreamWriter(value, os).write(offset, length);
        }
    }

    protected abstract StreamWriter<V> toStreamWriter(V value, OutputStream os);

    public abstract static class PathConfiguration<V> extends WriterConfiguration<V, Path> {
        private Function<Path, ? extends NewPathPolicy> newPathPolicy = CreateFileAndDirectories::new;

        public PathConfiguration<V> withNewFilePolicy(Function<Path, ? extends NewPathPolicy> newPathPolicy) {
            this.newPathPolicy = newPathPolicy;
            return this;
        }

        public PathConfiguration<V> withCreateFilePolicy() {
            return withNewFilePolicy(CreateOnlyFile::new);
        }

        public PathConfiguration<V> withDoNothingPolicy() {
            return withNewFilePolicy(_ -> DoNothing.get());
        }

        public Function<Path, ? extends NewPathPolicy> getNewPathPolicy() {
            return newPathPolicy != null ? newPathPolicy : CreateFileAndDirectories::new;
        }
    }
}
