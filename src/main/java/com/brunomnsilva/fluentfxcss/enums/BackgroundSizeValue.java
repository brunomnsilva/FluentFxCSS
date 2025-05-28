package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents common CSS keyword values for the {@code -fx-background-size} property.
 * This enum provides predefined constants for typical background sizing behaviors
 * like 'auto', 'cover', and 'contain'. For specific dimensions (e.g., length or
 * percentage pairs), a direct string value can be used with the appropriate
 * styler method.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/background-size">MDN background-size</a>
 * @see <a href="https://openjfx.io/javadoc/23/javafx.graphics/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-size)</a>
 *
 * @author Jules (AI Assistant)
 */
public enum BackgroundSizeValue {

    /**
     * The background image's original size is used.
     * This is the default value.
     */
    AUTO("auto"),

    /**
     * Scales the image as large as possible to fill the container, cropping if necessary
     * to maintain aspect ratio.
     */
    COVER("cover"),

    /**
     * Scales the image as large as possible without cropping or stretching the image,
     * fitting it inside the container. If the container's aspect ratio differs from
     * the image's, there may be empty space (letterboxing).
     */
    CONTAIN("contain");

    private final String cssValue;

    /**
     * Constructs a {@code BackgroundSizeValue} with the given CSS string representation.
     *
     * @param cssValue the string value as used in CSS stylesheets
     */
    BackgroundSizeValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the string representation of this background size value as used in CSS.
     *
     * @return the CSS string value (e.g., {@code "auto"}, {@code "cover"})
     */
    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return getCssValue();
    }
}
