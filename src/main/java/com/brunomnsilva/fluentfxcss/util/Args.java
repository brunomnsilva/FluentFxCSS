package com.brunomnsilva.fluentfxcss.util;

/**
 * Utility class providing static methods for validating method arguments.
 * <p>
 * This class offers common validation methods such as checking for non-null,
 * non-empty, pattern matching, prefix matching, and numeric interval constraints.
 * </p>
 * <p>
 * All methods throw {@link IllegalArgumentException} when a validation fails.
 * </p>
 *
 * @author brunomnsilva
 */
public final class Args {

    /**
     * Ensures that the given parameter is not {@code null}.
     *
     * @param param the parameter to check
     * @param name  the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code param} is {@code null}
     */
    public static void requireNotNull(Object param, String name) {
        if (param == null) {
            throw new IllegalArgumentException(String.format("Require '%s' to be not null.", name));
        }
    }

    /**
     * Ensures that the given string parameter is not empty (after trimming).
     *
     * @param param the string to check
     * @param name  the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code param} is empty or contains only whitespace
     */
    public static void requireNotEmpty(String param, String name) {
        if (param.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("Require '%s' to be not empty.", name));
        }
    }

    /**
     * Ensures that the given string parameter matches the specified regular expression.
     *
     * @param param the string to check
     * @param regex the regular expression to match
     * @param name  the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code param} does not match {@code regex}
     */
    public static void requireMatches(String param, String regex, String name) {
        if (!param.matches(regex)) {
            throw new IllegalArgumentException(String.format("'%s' must match '%s'.", name, regex));
        }
    }

    /**
     * Ensures that the given string parameter starts with the specified prefix.
     *
     * @param param      the string to check
     * @param startWith  the required prefix
     * @param name       the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code param} does not start with {@code startWith}
     */
    public static void requireStartsWith(String param, String startWith, String name) {
        if (!param.startsWith(startWith)) {
            throw new IllegalArgumentException(String.format("'%s' must start with '%s'.", name, startWith));
        }
    }

    /**
     * Ensures that the given numeric value lies within the specified interval [min, max].
     *
     * @param value the value to check
     * @param min   the minimum allowed value (inclusive)
     * @param max   the maximum allowed value (inclusive)
     * @param name  the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code value} is not within the interval
     */
    public static void requireInInterval(double value, double min, double max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format("Require '%s' in [%f, %f].", name, min, max));
        }
    }

    /**
     * Ensures that the given numeric value is finite (not NaN or infinite).
     *
     * @param value the value to check
     * @param name  the name of the parameter, used in the error message
     * @throws IllegalArgumentException if {@code value} is not finite
     */
    public static void requireFinite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(String.format("Require '%s' (%f) to be finite.", name, value));
        }
    }
}

