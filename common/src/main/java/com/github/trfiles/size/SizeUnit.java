package com.github.trfiles.size;

import java.util.Arrays;
import java.util.Optional;

public enum SizeUnit {
    /*
     * Unit of Measure  Bytes
     *     Kilobyte (kB)    1,000¹ = 1,000
     *     Megabyte (MB)    1,000² = 1,000,000
     *     Gigabyte (GB)    1,000³ = 1,000,000,000
     *     Terabyte (TB)    1,000⁴ = 1,000,000,000,000
     *     Petabyte (PB)    1,000⁵ = 1,000,000,000,000,000
     *     Exabyte (EB)     1,000⁶ = 1,000,000,000,000,000,000
     *     Zettabyte (ZB)   1,000⁷ = 1,000,000,000,000,000,000,000
     *     Yottabyte (YB)   1,000⁸ = 1,000,000,000,000,000,000,000,000
     *
     * Unit of Measure  Bytes
     *     Kibibyte (KiB)   1,024¹ = 1,024
     *     Mebibyte (MiB)   1,024² = 1,048,576
     *     Gibibyte (GiB)   1,024³ = 1,073,741,824
     *     Tebibyte (TiB)   1,024⁴ = 1,099,511,627,776
     *     Pebibyte (PiB)   1,024⁵ = 1,125,899,906,842,624
     *     Exbibyte (EiB)   1,024⁶ = 1,152,921,504,606,846,976
     *     Zebibyte (ZiB)   1,024⁷ = 1,180,591,620,717,113,034,240
     *     Yebibyte (YiB)   1,024⁸ = 1,208,925,819,614,629,174,706,176
     */

    BYTE(1, "B"),

    /**
     * Decimal Units
     */
    KILOBYTE(1000, "KB"),
    MEGABYTE(Math.pow(1000, 2), "MB"),
    GIGABYTE(Math.pow(1000, 3), "GB"),
    TERABYTE(Math.pow(1000, 4), "TB"),
    PETABYTE(Math.pow(1000, 5), "PB"),
    /*
     * Useless - Used only for conversion or for showing something
     */
    EXABYTE(Math.pow(1000, 6), "EB"),
    ZETTABYTE(Math.pow(1000, 7), "ZB"),
    YOTTABYTE(Math.pow(1000, 8), "YB"),

    /**
     * Binary Units
     */
    KIBIBYTE(1024, "KiB"),
    MEBIBYTE(Math.pow(1024, 2), "MiB"),
    GIBIBYTE(Math.pow(1024, 3), "GiB"),
    TEBIBYTE(Math.pow(1024, 4), "TiB"),
    PEBIBYTE(Math.pow(1024, 5), "PiB"),
    /*
     * Useless - Used only for conversion or for showing something
     */
    EXBIBYTE(Math.pow(1024, 6), "EiB"),
    ZEBIBYTE(Math.pow(1024, 7), "ZiB"),
    YOBIBYTE(Math.pow(1024, 8), "YiB");


    private final double bytes;
    private final String tag;

    SizeUnit(double bytes, String tag) {
        this.bytes = bytes;
        this.tag = tag;
    }

    /**
     * Parses a string into a SizeUnit.
     *
     * @param str The string to parse.
     * @return The parsed SizeUnit, or null if not found.
     */
    public static Optional<SizeUnit> parse(String str) {
        try {
            return Optional.of(SizeUnit.valueOf(str.toUpperCase()));
        } catch (IllegalArgumentException _) {
            return Arrays.stream(SizeUnit.values()).filter(unit -> unit.getTag().equalsIgnoreCase(str)).findFirst();
        }
    }

    /**
     * Gets the number of bytes in this unit.
     *
     * @return The number of bytes.
     */
    public double getBytes() {
        return bytes;
    }

    /**
     * Gets the tag of this unit.
     *
     * @return The tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Converts this unit to another unit.
     *
     * @param unit The unit to convert to.
     * @return The converted value.
     */
    public double to(SizeUnit unit) {
        return this.getBytes() / unit.getBytes();
    }

    /**
     * Converts an amount of this unit to bytes.
     *
     * @param amount The amount to convert.
     * @return The amount in bytes.
     */
    public double toBytes(double amount) {
        return amount * this.getBytes();
    }

    /**
     * Converts an amount in bytes to this unit.
     *
     * @param bytes The amount in bytes.
     * @return The amount in this unit.
     */
    public double fromBytes(double bytes) {
        return bytes / this.getBytes();
    }
}
