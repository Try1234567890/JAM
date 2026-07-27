package me.tr.trfiles.management.io.writer.stream;

import me.tr.trfiles.management.io.writer.Writer;

import java.io.IOException;
import java.io.OutputStream;

public abstract class ToStreamWriter<V> extends Writer<V, OutputStream> {

    public void writeRangeOrThrownAndClose(OutputStream out, V value, int from, int to) throws IOException {
        super.writeRangeOrThrown(value, out, from, to);
        out.close();
    }

    public final void writeFromOrThrownAndClose(OutputStream out, V value, int from) throws IOException {
        super.writeFromOrThrown(value, out, from);
        out.close();
    }

    public final void writeToOrThrownAndClose(OutputStream out, V value, int to) throws IOException {
        super.writeToOrThrown(value, out, to);
        out.close();
    }

    public final void writeOrThrownAndClose(OutputStream out, V value) throws IOException {
        super.writeOrThrown(value, out);
        out.close();
    }

    public final boolean writeRangeAndClose(OutputStream out, V value, int from, int to) {
        try {
            super.writeRange(value, out, from, to);
            out.close();
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public final boolean writeFromAndClose(OutputStream out, V value, int from) {
        try {
            super.writeFrom(value, out, from);
            out.close();
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public final boolean writeToAndClose(OutputStream out, V value, int to) {
        try {
            super.writeTo(value, out, to);
            out.close();
            return true;
        } catch (IOException _) {
        }
        return false;
    }

    public final boolean writeAndClose(OutputStream out, V value) {
        try {
            super.write(value, out);
            out.close();
            return true;
        } catch (IOException _) {
        }
        return false;
    }
}
