package com.github.trfiles.management.io.writer;


import com.github.trfiles.management.io.writer.file.*;
import com.github.trfiles.management.io.writer.path.*;
import com.github.trfiles.management.io.writer.stream.*;
import com.github.utilities.validators.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public class Writers {
    private Writers() {
    }

    public static InstanceWriter<byte[], File> newBytesWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), BytesToFileWriter.getInstance());
    }

    public static InstanceWriter<char[], File> newCharsWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), CharsToFileWriter.getInstance());
    }

    public static InstanceWriter<InputStream, File> newStreamWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), StreamToFileWriter.getInstance());
    }

    public static InstanceWriter<String[], File> newStringArrayWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), StringArrayToFileWriter.getInstance());
    }

    public static InstanceWriter<List<String>, File> newStringListWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), StringListToFileWriter.getInstance());
    }

    public static InstanceWriter<String, File> newStringWriter(String path) {
        return new InstanceWriter<>(new File(Preconditions.parameterNotNull(path, "path")), StringToFileWriter.getInstance());
    }

    public static InstanceWriter<byte[], File> newBytesWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), BytesToFileWriter.getInstance());
    }

    public static InstanceWriter<char[], File> newCharsWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), CharsToFileWriter.getInstance());
    }

    public static InstanceWriter<InputStream, File> newStreamWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StreamToFileWriter.getInstance());
    }

    public static InstanceWriter<String[], File> newStringArrayWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringArrayToFileWriter.getInstance());
    }

    public static InstanceWriter<List<String>, File> newStringListWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringListToFileWriter.getInstance());
    }

    public static InstanceWriter<String, File> newStringWriter(File path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringToFileWriter.getInstance());
    }

    public static InstanceWriter<byte[], Path> newBytesWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), BytesToPathWriter.getInstance());
    }

    public static InstanceWriter<char[], Path> newCharsWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), CharsToPathWriter.getInstance());
    }

    public static InstanceWriter<InputStream, Path> newStreamWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StreamToPathWriter.getInstance());
    }

    public static InstanceWriter<String[], Path> newStringArrayWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringArrayToPathWriter.getInstance());
    }

    public static InstanceWriter<List<String>, Path> newStringListWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringListToPathWriter.getInstance());
    }

    public static InstanceWriter<String, Path> newStringWriter(Path path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringToPathWriter.getInstance());
    }

    public static InstanceWriter<byte[], OutputStream> newBytesWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), BytesToStreamWriter.getInstance());
    }

    public static InstanceWriter<char[], OutputStream> newCharsWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), CharsToStreamWriter.getInstance());
    }

    public static InstanceWriter<InputStream, OutputStream> newStreamWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StreamToStreamWriter.getInstance());
    }

    public static InstanceWriter<String[], OutputStream> newStringArrayWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringArrayToStreamWriter.getInstance());
    }

    public static InstanceWriter<List<String>, OutputStream> newStringListWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringListToStreamWriter.getInstance());
    }

    public static InstanceWriter<String, OutputStream> newStringWriter(OutputStream path) {
        return new InstanceWriter<>(Preconditions.parameterNotNull(path, "path"), StringToStreamWriter.getInstance());
    }

    public static BytesToFileWriter getBytesToFileWriter() {
        return BytesToFileWriter.getInstance();
    }

    public static CharsToFileWriter getCharsToFileWriter() {
        return CharsToFileWriter.getInstance();
    }

    public static StreamToFileWriter getStreamToFileWriter() {
        return StreamToFileWriter.getInstance();
    }

    public static StringArrayToFileWriter getStringArrayToFileWriter() {
        return StringArrayToFileWriter.getInstance();
    }

    public static StringListToFileWriter getStringListToFileWriter() {
        return StringListToFileWriter.getInstance();
    }

    public static StringToFileWriter getStringToFileWriter() {
        return StringToFileWriter.getInstance();
    }

    public static BytesToPathWriter getBytesToPathWriter() {
        return BytesToPathWriter.getInstance();
    }

    public static CharsToPathWriter getCharsToPathWriter() {
        return CharsToPathWriter.getInstance();
    }

    public static StreamToPathWriter getStreamToPathWriter() {
        return StreamToPathWriter.getInstance();
    }

    public static StringArrayToPathWriter getStringArrayToPathWriter() {
        return StringArrayToPathWriter.getInstance();
    }

    public static StringListToPathWriter getStringListToPathWriter() {
        return StringListToPathWriter.getInstance();
    }

    public static StringToPathWriter getStringToPathWriter() {
        return StringToPathWriter.getInstance();
    }

    public static BytesToStreamWriter getBytesToStreamWriter() {
        return BytesToStreamWriter.getInstance();
    }

    public static CharsToStreamWriter getCharsToStreamWriter() {
        return CharsToStreamWriter.getInstance();
    }

    public static StreamToStreamWriter getStreamToStreamWriter() {
        return StreamToStreamWriter.getInstance();
    }

    public static StringArrayToStreamWriter getStringArrayToStreamWriter() {
        return StringArrayToStreamWriter.getInstance();
    }

    public static StringListToStreamWriter getStringListToStreamWriter() {
        return StringListToStreamWriter.getInstance();
    }

    public static StringToStreamWriter getStringToStreamWriter() {
        return StringToStreamWriter.getInstance();
    }
}
