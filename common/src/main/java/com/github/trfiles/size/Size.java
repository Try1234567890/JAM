package com.github.trfiles.size;

import com.github.utilities.validators.Preconditions;
import com.github.trfiles.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Size(double amount, SizeUnit unit) {
    private static final Pattern REGEX_PATTERN = Pattern.compile("(\\d+\\s*)([a-zA-Z]{1,3})");

    public Size(double amount, SizeUnit unit) {
        this.amount = amount;
        this.unit = Preconditions.simpleParameterNotNull(unit, "unit");
    }

    /**
     * Create a Size object from a File's length in bytes.
     * <p>
     * If an I/O error occurs while reading, a new {@link Size}
     * with {@code length 0} is returned.
     *
     * @param strPath The path to get the size from.
     * @return A Size object representing the path's size in bytes.
     */
    public static Size file(String strPath) {
        Preconditions.parameterNotNull(strPath, "strPath", "The path cannot be empty or null.");
        try {
            Path path = Paths.get(strPath);
            return file(path);
        } catch (InvalidPathException _) {
            return new Size(0, SizeUnit.BYTE);
        }
    }

    /**
     * Create a Size object from a File's length in bytes.
     * <p>
     * If an I/O error occurs while reading, a new {@link Size}
     * with {@code length 0} is returned.
     *
     * @param path The path to get the size from.
     * @return A Size object representing the path's size in bytes.
     */
    public static Size file(Path path) {
        Preconditions.parameterNotNull(path, "path");
        try {
            double size = Files.size(path);
            return new Size(size, SizeUnit.BYTE);
        } catch (IOException _) {
            return new Size(0D, SizeUnit.BYTE);
        }
    }

    /**
     * Create a Size object from a File's length in bytes.
     * <p>
     * If an I/O error occurs while reading, a new {@link Size}
     * with {@code length 0} is returned.
     *
     * @param file The file to get the size from.
     * @return A Size object representing the file's size in bytes.
     */
    public static Size file(File file) {
        Preconditions.parameterNotNull(file, "file");
        double size = file.length();
        return new Size(size, SizeUnit.BYTE);
    }

    /**
     * Parse a Size from a string.
     *
     * @param str The string to parse.
     * @return A Size object or null if the string is invalid.
     */
    public static Size parse(String str) {
        Preconditions.parameterNotNull(str, "str");

        Matcher matcher = REGEX_PATTERN.matcher(str);

        if (matcher.matches()) {
            String amt = Preconditions.completeNotNull(matcher.group(1), "The amount of size found inside \"" + str + "\" is invalid!");
            String unitStr = Preconditions.completeNotNull(matcher.group(2), "The unit of size found inside \"" + str + "\" is invalid!");

            long amount = Utility.parseLong(amt.trim());
            SizeUnit unit = SizeUnit.parse(unitStr.trim());

            if (unit == null
                    || amount < 0)
                return null;


            return new Size(amount, unit);
        }

        return null;
    }

    /**
     * Get the amount of the size.
     *
     * @return The amount of the size.
     */
    @Override
    public double amount() {
        return amount;
    }

    /**
     * Get the unit of the size.
     *
     * @return The unit of the size.
     */
    @Override
    public SizeUnit unit() {
        return unit;
    }

    /**
     * Convert the size to the specified unit.
     *
     * @param unit The unit to convert to.
     * @return The size in the specified unit.
     */
    public double to(SizeUnit unit) {
        return amount * unit().to(unit);
    }

    /**
     * Convert the size to bytes.
     *
     * @return The size in bytes.
     */
    public double toByte() {
        return to(SizeUnit.BYTE);
    }

    /**
     * Convert the size to kilobytes.
     *
     * @return The size in kilobytes.
     */
    public double toKilo() {
        return to(SizeUnit.KILOBYTE);
    }

    /**
     * Convert the size to megabytes.
     *
     * @return The size in megabytes.
     */
    public double toMega() {
        return to(SizeUnit.MEGABYTE);
    }

    /**
     * Convert the size to gigabytes.
     *
     * @return The size in gigabytes.
     */
    public double toGiga() {
        return to(SizeUnit.GIGABYTE);
    }

    /**
     * Convert the size to terabytes.
     *
     * @return The size in terabytes.
     */
    public double toTera() {
        return to(SizeUnit.TERABYTE);
    }

    /**
     * Convert the size to petabytes.
     *
     * @return The size in petabytes.
     */
    public double toPeta() {
        return to(SizeUnit.PETABYTE);
    }

    /**
     * Convert the size to kibibytes.
     *
     * @return The size in kibibytes.
     */
    public double toKibi() {
        return to(SizeUnit.KIBIBYTE);
    }

    /**
     * Convert the size to mebibytes.
     *
     * @return The size in mebibytes.
     */
    public double toMebi() {
        return to(SizeUnit.MEBIBYTE);
    }

    /**
     * Convert the size to gibibytes.
     *
     * @return The size in gibibytes.
     */
    public double toGibi() {
        return to(SizeUnit.GIBIBYTE);
    }

    /**
     * Convert the size to tebibytes.
     *
     * @return The size in tebibytes.
     */
    public double toTebi() {
        return to(SizeUnit.TEBIBYTE);
    }

    /**
     * Convert the size to pebibytes.
     *
     * @return The size in pebibytes.
     */
    public double toPebi() {
        return to(SizeUnit.PEBIBYTE);
    }

    @Override
    public String toString() {
        return "TrSize{" + amount + " " + unit + '}';
    }

    public boolean isMajorOrEquals(Size size) {
        return isMajor(size) || isEquals(size);
    }

    public boolean isMajor(Size size) {
        return Double.compare(to(SizeUnit.BYTE), size.to(SizeUnit.BYTE)) == 1;
    }

    public boolean isEquals(Size size) {
        return Double.compare(to(SizeUnit.BYTE), size.to(SizeUnit.BYTE)) == 0;
    }

    public boolean isMinorOrEquals(Size size) {
        return isMinor(size) || isEquals(size);
    }

    public boolean isMinor(Size size) {
        return Double.compare(to(SizeUnit.BYTE), size.to(SizeUnit.BYTE)) == -1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount(), unit());
    }
}
