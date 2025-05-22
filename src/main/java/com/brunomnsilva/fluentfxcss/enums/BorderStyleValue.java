package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents standard CSS border style values valid JavaFX style definitions.
 * <p>
 * Each enum constant corresponds to a valid CSS keyword used to define
 * the appearance of a border in JavaFX stylesheets.
 * This abstraction simplifies usage and conversion to CSS strings
 * in programmatic style definitions.
 *
 * <p><strong>Supported Styles:</strong></p>
 * <ul>
 *   <li>{@link #NONE} — No border.</li>
 *   <li>{@link #DOTTED} — A series of dots.</li>
 *   <li>{@link #DASHED} — A series of short dashes.</li>
 *   <li>{@link #SOLID} — A solid, continuous line.</li>
 * </ul>
 *
 * @author brunomnsilva
 */
public enum BorderStyleValue {

    /** No border. */
    NONE("none"),

    /** A dotted border. */
    DOTTED("dotted"),

    /** A dashed border. */
    DASHED("dashed"),

    /** A solid border. */
    SOLID("solid");

    private final String cssValue;

    /**
     * Constructs a {@code BorderStyleValue} with its corresponding CSS string.
     *
     * @param cssValue the CSS keyword for the border style
     */
    BorderStyleValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the CSS keyword associated with this border style.
     *
     * @return a valid CSS string such as {@code "dotted"}, {@code "solid"}, etc.
     */
    public String getCssValue() {
        return cssValue;
    }
}
