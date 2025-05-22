package com.brunomnsilva.fluentfxcss.enums;

/**
 * Represents common JavaFX CSS pseudo-classes applicable for styling node states.
 *
 * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#pseudoclasses">JavaFX CSS Pseudo-classes</a>
 */
public enum PseudoClassValue {
    HOVER(":hover"),
    FOCUSED(":focused"),
    DISABLED(":disabled");

    // TODO: Potentially others if deemed broadly applicable to Node/Region/Shape/Text
    // PRESSED(":pressed"), // Typically for interactive controls
    // SELECTED(":selected"), // More for list/table cells, ToggleButton
    // ARMED(":armed");       // More for ButtonBase

    private final String cssSelectorSuffix;

    PseudoClassValue(String cssSelectorSuffix) {
        this.cssSelectorSuffix = cssSelectorSuffix;
    }

    /**
     * Gets the CSS selector suffix for this pseudo-class (e.g., ":hover").
     *
     * @return The CSS pseudo-class selector string.
     */
    public String getCssSelectorSuffix() {
        return cssSelectorSuffix;
    }
}
