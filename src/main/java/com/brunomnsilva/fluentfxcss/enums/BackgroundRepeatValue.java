package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents the possible CSS values for the {@code -fx-background-repeat} property.
 * This controls how a background image is repeated.
 *
 * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-repeat)</a>
 */
public enum BackgroundRepeatValue {
    /** The image is repeated both horizontally and vertically. */
    REPEAT("repeat"),
    /** The image is repeated horizontally only. */
    REPEAT_X("repeat-x"),
    /** The image is repeated vertically only. */
    REPEAT_Y("repeat-y"),
    /** The image is not repeated; it is drawn only once. */
    NO_REPEAT("no-repeat"),
    /** The image is repeated as much as possible without clipping, and a space is distributed between the tiles. */
    SPACE("space"), // Less common, but in the spec
    /** The image is repeated as much as possible without clipping, and the image is rescaled to fill the space. */
    ROUND("round"), // Less common, but in the spec
    /** Inherits the repeat style from its parent. */
    INHERIT("inherit");

    private final String cssValue;

    BackgroundRepeatValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Gets the CSS string representation of this background repeat value.
     * @return The CSS value (e.g., "repeat", "no-repeat").
     */
    public String getCssValue() {
        return cssValue;
    }
}
