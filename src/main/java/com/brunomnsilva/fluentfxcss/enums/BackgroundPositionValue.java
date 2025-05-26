package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents common CSS keyword values for the {@code -fx-background-position} property.
 * This enum provides predefined constants for typical background image positions.
 * For more complex positions (e.g., percentages or length units), a direct string
 * value can be used with the appropriate styler method.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/background-position">MDN background-position</a>
 * @see <a href="https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-position)</a>
 *
 * @author Jules (AI Assistant)
 */
public enum BackgroundPositionValue {

    /** Equivalent to '0% 0%'. */
    LEFT_TOP("left top"), // Or TOP_LEFT, but CSS usually puts horizontal first
    /** Equivalent to '0% 50%'. */
    LEFT_CENTER("left center"),
    /** Equivalent to '0% 100%'. */
    LEFT_BOTTOM("left bottom"),

    /** Equivalent to '50% 0%'. */
    CENTER_TOP("center top"),
    /** Equivalent to '50% 50%'. */
    CENTER_CENTER("center center"),
    /** Equivalent to '50% 100%'. */
    CENTER_BOTTOM("center bottom"),

    /** Equivalent to '100% 0%'. */
    RIGHT_TOP("right top"),
    /** Equivalent to '100% 50%'. */
    RIGHT_CENTER("right center"),
    /** Equivalent to '100% 100%'. */
    RIGHT_BOTTOM("right bottom"),

    // Single keyword convenience, often expanded by browsers or CSS engines
    // but useful to have as direct representations of common settings.
    // CSS spec defines single values like 'left' as 'left center'.
    /** Equivalent to '0% 50%' or 'left center'. */
    LEFT("left"),
    /** Equivalent to '100% 50%' or 'right center'. */
    RIGHT("right"),
    /** Equivalent to '50% 0%' or 'center top'. */
    TOP("top"),
    /** Equivalent to '50% 100%' or 'center bottom'. */
    BOTTOM("bottom"),
    /** Equivalent to '50% 50%' or 'center center'. */
    CENTER("center");


    private final String cssValue;

    /**
     * Constructs a {@code BackgroundPositionValue} with the given CSS string representation.
     *
     * @param cssValue the string value as used in CSS stylesheets
     */
    BackgroundPositionValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the string representation of this background position value as used in CSS.
     *
     * @return the CSS string value (e.g., {@code "left top"}, {@code "center"})
     */
    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return getCssValue();
    }
}
