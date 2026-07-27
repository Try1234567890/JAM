package me.tr.trfiles.management.io.writer;

import com.github.utilities.validators.Preconditions;
import me.tr.trfiles.management.io.IOProcess;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class Writer<V, O> extends IOProcess<V> {

    protected abstract void writeRangeOrThrown0(@NotNull V value, @NotNull O out, int from, int to) throws IOException;

    public void writeRangeOrThrown(V value, O out, int from, int to) throws IOException {
        Preconditions.parameterNotNull(value, "value");
        Preconditions.parameterNotNull(out, "output");

        checkIndexes(value, from, to);
        writeRangeOrThrown0(value, out, from, to);
    }

    public void writeFromOrThrown(V value, O out, int from) throws IOException {
        writeRangeOrThrown(value, out, from, size(value));
    }

    public void writeToOrThrown(V value, O out, int to) throws IOException {
        writeRangeOrThrown(value, out, 0, to);
    }

    public void writeOrThrown(V value, O out) throws IOException {
        writeRangeOrThrown(value, out, 0, size(value));
    }

    public boolean writeRange(V value, O out, int from, int to) {
        try {
            writeRangeOrThrown(value, out, from, to);
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public boolean writeFrom(V value, O out, int from) {
        try {
            writeRangeOrThrown(value, out, from, size(value));
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public boolean writeTo(V value, O out, int to) {
        try {
            writeRangeOrThrown(value, out, 0, to);
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public boolean write(V value, O out) {
        try {
            writeRangeOrThrown(value, out, 0, size(value));
            return true;
        } catch (IOException _) {
        }
        return false;
    }
}
