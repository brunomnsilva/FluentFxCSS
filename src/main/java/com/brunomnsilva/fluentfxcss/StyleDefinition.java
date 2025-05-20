package com.brunomnsilva.fluentfxcss;

import javafx.scene.Node;
import java.util.Map;

public interface StyleDefinition {
    /**
     * Applies the defined styles to the given Node.
     * @param node The Node to style.
     * @return The styled Node, for chaining if desired.
     */
    <T extends Node> T applyTo(T node);

    /**
     * Gets the generated CSS string for this style definition.
     * @return The CSS string.
     */
    String getCssString();

    /**
     * Gets the underlying map of CSS properties and values.
     * Primarily for internal use or advanced scenarios like merging.
     * @return An unmodifiable map of styles.
     */
    Map<String, String> getStylesMap();
}
