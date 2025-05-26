package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents CSS values for the {@code -fx-background-repeat} property.
 * This enum provides predefined constants for common background repeat behaviors.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/background-repeat">MDN background-repeat</a>
 * @see <a href="https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-repeat)</a>
 *
 * @author Jules (AI Assistant)
 */
public enum BackgroundRepeatValue {

    /** The image is repeated as much as needed to cover the background painting area. */
    REPEAT("repeat"),

    /** The image is not repeated: only one copy of the image is drawn. */
    NO_REPEAT("no-repeat"),

    /** The image is repeated horizontally but not vertically. */
    REPEAT_X("repeat-x"),

    /** The image is repeated vertically but not horizontally. */
    REPEAT_Y("repeat-y"),

    /** The image is repeated as much as possible without clipping. The first and last images are pinned to either side of the element, and whitespace is distributed evenly between the images. */
    SPACE("space"),

    /** The image is repeated as much as possible without clipping. If it doesn’t fill the container with a whole number of tiles, the images are rescaled so that they do. */
    ROUND("round");

    private final String cssValue;

    /**
     * Constructs a {@code BackgroundRepeatValue} with the given CSS string representation.
     *
     * @param cssValue the string value as used in CSS stylesheets
     */
    BackgroundRepeatValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the string representation of this background repeat value as used in CSS.
     *
     * @return the CSS string value (e.g., {@code "no-repeat"}, {@code "repeat-x"})
     */
    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return getCssValue();
    }
}
