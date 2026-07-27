package com.github.trfiles.management.io.reader;


import com.github.utilities.validators.Preconditions;
import com.github.trfiles.management.io.reader.file.BytesFileReader;
import com.github.trfiles.management.io.reader.file.CharsFileReader;
import com.github.trfiles.management.io.reader.file.StringFileReader;
import com.github.trfiles.management.io.reader.file.StringListFileReader;
import com.github.trfiles.management.io.reader.path.BytesPathReader;
import com.github.trfiles.management.io.reader.path.CharsPathReader;
import com.github.trfiles.management.io.reader.path.StringListPathReader;
import com.github.trfiles.management.io.reader.path.StringPathReader;
import com.github.trfiles.management.io.reader.stream.BytesStreamReader;
import com.github.trfiles.management.io.reader.stream.CharsStreamReader;
import com.github.trfiles.management.io.reader.stream.StringListStreamReader;
import com.github.trfiles.management.io.reader.stream.StringStreamReader;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public class Readers {
    private Readers() {
    }

    public static InstanceReader<InputStream, byte[]> newStreamReaderAsBytes(InputStream is) {
        return new InstanceReader<>(Preconditions.parameterNotNull(is, "is"), BytesStreamReader.getInstance());
    }

    public static InstanceReader<InputStream, char[]> newStreamReaderAsChars(InputStream is) {
        return new InstanceReader<>(Preconditions.parameterNotNull(is, "is"), CharsStreamReader.getInstance());
    }

    public static InstanceReader<InputStream, List<String>> newStreamReaderAsStringList(InputStream is) {
        return new InstanceReader<>(Preconditions.parameterNotNull(is, "is"), StringListStreamReader.getInstance());
    }

    public static InstanceReader<InputStream, String> newStreamReaderAsString(InputStream is) {
        return new InstanceReader<>(Preconditions.parameterNotNull(is, "is"), StringStreamReader.getInstance());
    }

    public static InstanceReader<Path, byte[]> newPathReaderAsBytes(Path path) {
        return new InstanceReader<>(Preconditions.parameterNotNull(path, "path"), BytesPathReader.getInstance());
    }

    public static InstanceReader<Path, char[]> newPathReaderAsChars(Path path) {
        return new InstanceReader<>(Preconditions.parameterNotNull(path, "path"), CharsPathReader.getInstance());
    }

    public static InstanceReader<Path, List<String>> newPathReaderAsStringList(Path path) {
        return new InstanceReader<>(Preconditions.parameterNotNull(path, "path"), StringListPathReader.getInstance());
    }

    public static InstanceReader<Path, String> newPathReaderAsString(Path path) {
        return new InstanceReader<>(Preconditions.parameterNotNull(path, "path"), StringPathReader.getInstance());
    }

    public static InstanceReader<File, byte[]> newFileReaderAsBytes(File file) {
        return new InstanceReader<>(Preconditions.parameterNotNull(file, "file"), BytesFileReader.getInstance());
    }

    public static InstanceReader<File, char[]> newFileReaderAsChars(File file) {
        return new InstanceReader<>(Preconditions.parameterNotNull(file, "file"), CharsFileReader.getInstance());
    }

    public static InstanceReader<File, List<String>> newFileReaderAsStringList(File file) {
        return new InstanceReader<>(Preconditions.parameterNotNull(file, "file"), StringListFileReader.getInstance());
    }

    public static InstanceReader<File, String> newFileReaderAsString(File file) {
        return new InstanceReader<>(Preconditions.parameterNotNull(file, "file"), StringFileReader.getInstance());
    }

    public static InstanceReader<File, byte[]> newStringPathReaderAsBytes(String path) {
        return newFileReaderAsBytes(new File(Preconditions.parameterNotNull(path, "path")));
    }

    public static InstanceReader<File, char[]> newStringPathReaderAsChars(String path) {
        return newFileReaderAsChars(new File(Preconditions.parameterNotNull(path, "path")));
    }

    public static InstanceReader<File, List<String>> newStringPathReaderAsStringList(String path) {
        return newFileReaderAsStringList(new File(Preconditions.parameterNotNull(path, "path")));
    }

    public static InstanceReader<File, String> newStringPathReaderAsString(String path) {
        return newFileReaderAsString(new File(Preconditions.parameterNotNull(path, "path")));
    }

    public static BytesStreamReader getBytesStreamReader() {
        return BytesStreamReader.getInstance();
    }

    public static CharsStreamReader getCharsStreamReader() {
        return CharsStreamReader.getInstance();
    }

    public static StringListStreamReader getStringListStreamReader() {
        return StringListStreamReader.getInstance();
    }

    public static StringStreamReader getStringStreamReader() {
        return StringStreamReader.getInstance();
    }

    public static BytesPathReader getBytesPathReader() {
        return BytesPathReader.getInstance();
    }

    public static CharsPathReader getCharsPathReader() {
        return CharsPathReader.getInstance();
    }

    public static StringListPathReader getStringListPathReader() {
        return StringListPathReader.getInstance();
    }

    public static StringPathReader getStringPathReader() {
        return StringPathReader.getInstance();
    }

    public static BytesFileReader getBytesFileReader() {
        return BytesFileReader.getInstance();
    }

    public static CharsFileReader getCharsFileReader() {
        return CharsFileReader.getInstance();
    }

    public static StringListFileReader getStringListFileReader() {
        return StringListFileReader.getInstance();
    }

    public static StringFileReader getStringFileReader() {
        return StringFileReader.getInstance();
    }
}
