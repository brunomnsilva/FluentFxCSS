package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents CSS unit types used in JavaFX style definitions.
 * <p>
 * Each enum constant corresponds to a CSS unit and provides its textual representation
 * as used in stylesheets. These units can be relative (e.g., {@code em}, {@code ex}),
 * absolute (e.g., {@code cm}, {@code in}), or percentage-based (e.g., {@code %}).
 * <p>
 * This enum is useful for formatting CSS values with appropriate units when
 * generating styles programmatically.
 *
 * <p><strong>CSS Unit Categories:</strong></p>
 * <ul>
 *   <li><b>Relative:</b> {@link #PX}, {@link #EM}, {@link #EX}</li>
 *   <li><b>Absolute:</b> {@link #IN}, {@link #CM}, {@link #MM}, {@link #PT}, {@link #PC}</li>
 *   <li><b>Percentage:</b> {@link #PERCENT}</li>
 * </ul>
 *
 * @author brunomnsilva
 */
public enum UnitValue {

    /** Pixels — relative to the viewing device (default unit in many cases). */
    PX("px"),

    /** Em — relative to the font-size of the element. */
    EM("em"),

    /** Ex — relative to the x-height of the current font. */
    EX("ex"),

    /** Inches — 1in is equal to 2.54cm. */
    IN("in"),

    /** Centimeters. */
    CM("cm"),

    /** Millimeters. */
    MM("mm"),

    /** Points — 1pt is equal to 1/72 of 1in. */
    PT("pt"),

    /** Picas — 1pc is equal to 12pt. */
    PC("pc"),

    /** Percentage — relative to the parent value. */
    PERCENT("%"),

    NONE("");

    private final String cssValue;

    /**
     * Constructs a {@code UnitValue} with the given CSS string representation.
     *
     * @param cssValue the string value as used in CSS stylesheets
     */
    UnitValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the string representation of this unit as used in CSS.
     *
     * @return the CSS unit string (e.g., {@code "px"}, {@code "%"})
     */
    public String getCssValue() {
        return cssValue;
    }
}
