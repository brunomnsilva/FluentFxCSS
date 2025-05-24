package com.brunomnsilva.fluentfxcss.enums;

import javafx.scene.text.Text;

/**
 * Represents the possible CSS values for the {@code -fx-text-origin} property,
 * which determines the vertical origin of the text when it is drawn.
 * <p>
 * The text origin influences how the {@code y} coordinate of a {@link Text} node
 * is interpreted. For example, if {@code -fx-text-origin} is {@code BASELINE},
 * the {@code y} coordinate specifies the position of the text's baseline.
 * If it is {@code TOP}, the {@code y} coordinate specifies the top edge of the text.
 * </p>
 * This enum provides type-safe constants for these CSS values. It conceptually
 * aligns with the {@link javafx.geometry.VPos} enum used in JavaFX's programmatic API,
 * though {@code VPos} also includes {@code VPos.CENTER}.
 *
 * @see com.brunomnsilva.fluentfxcss.stylers.TextStyler#origin(TextOriginValue)
 * @see javafx.geometry.VPos
 * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#text">JavaFX CSS Text (-fx-text-origin)</a>
 */
public enum TextOriginValue {

    /**
     * The origin of the text is its baseline.
     * When drawn, the {@code y} coordinate of the {@link Text} node will correspond
     * to the baseline of the first line of text.
     * Corresponds to the CSS value {@code "baseline"}.
     */
    BASELINE("baseline"),

    /**
     * The origin of the text is its top edge.
     * When drawn, the {@code y} coordinate of the {@link Text} node will correspond
     * to the top of the text bounds.
     * Corresponds to the CSS value {@code "top"}.
     */
    TOP("top"),

    /**
     * The origin of the text is its bottom edge.
     * When drawn, the {@code y} coordinate of the {@link Text} node will correspond
     * to the bottom of the text bounds.
     * Corresponds to the CSS value {@code "bottom"}.
     */
    BOTTOM("bottom");

    private final String cssValue;

    /**
     * Constructs a {@code TextOriginValue} with the given CSS string representation.
     *
     * @param cssValue The string value as used in CSS stylesheets (e.g., "baseline", "top", "bottom").
     */
    TextOriginValue(String cssValue) {
        this.cssValue = cssValue;
    }

    /**
     * Returns the string representation of this text origin value as used in CSS.
     *
     * @return The CSS string (e.g., {@code "baseline"}, {@code "top"}, {@code "bottom"}).
     */
    public String getCssValue() {
        return cssValue;
    }
}
