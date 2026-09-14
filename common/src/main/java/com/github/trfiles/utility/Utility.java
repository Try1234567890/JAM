package com.github.trfiles.utility;

import com.github.utilities.validators.Preconditions;

import java.util.Arrays;

public class Utility {
    private Utility() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class.");
    }

    public static final int DEF_RADIX = 10;
    public static final int THREADS_AMOUNT = Runtime.getRuntime().availableProcessors();
    public static final String NEWLINE = System.lineSeparator();

    /**
     * Retrieves all lines of {@code content}.
     * <p>
     * The content will be split by the newline character.
     *
     * @param content The content to retrieve the lines from.
     * @return An array of lines.
     */
    public static String[] getAllLines(String content) {
        return Preconditions.simpleNotNull(content, "").split(NEWLINE, -1);
    }

    /**
     * Retrieves all the non-empty lines of the {@code content}.
     * <p>
     * The content will be split by the newline character.
     *
     * @param content The content to retrieve the lines from.
     * @return An array of non-empty lines.
     */
    public static String[] getLines(String content) {
        return Arrays.stream(getAllLines(content))
                .filter(line -> !line.trim().isEmpty())
                .toArray(String[]::new);
    }

    /**
     * Parse a float from a string.
     * <p>
     * If the parse fails, {@code -1} will be returned.
     *
     * @param str The string to parse float from.
     * @return A float if no error occurs, otherwise {@code -1}.
     */
    public static float parseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }

    /**
     * Parse a long from a string.
     * <p>
     * If the parse fails, {@code -1} will be returned.
     *
     * @param str The string to parse long from.
     * @return A long if no error occurs, otherwise {@code -1}.
     */
    public static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }
}
