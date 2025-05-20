package com.brunomnsilva.fluentfxcss;

import javafx.scene.Node;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseStyleDefinition implements StyleDefinition {
    protected final Map<String, String> styles; // Should be immutable or a copy

    public BaseStyleDefinition(Map<String, String> styles) {
        // Ensure immutability or use an immutable map copy
        this.styles = Collections.unmodifiableMap(styles);
    }

    @Override
    public <T extends Node> T applyTo(T node) {
        if (node != null) {
            node.setStyle(getCssString());
        }
        return node;
    }

    @Override
    public String getCssString() {
        return styles.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + ";")
                .collect(Collectors.joining(" "));
    }

    @Override
    public Map<String, String> getStylesMap() {
        return styles; // Already unmodifiable
    }


    /**
     * Creates a new com.brunomnsilva.fluentfxcss.StyleDefinition by merging this style with another.
     * Properties from 'other' will override properties from 'this' if they conflict.
     * @param other The other com.brunomnsilva.fluentfxcss.StyleDefinition to merge.
     * @return A new, merged com.brunomnsilva.fluentfxcss.StyleDefinition.
     */
    public StyleDefinition mergeWith(StyleDefinition other) {
        Map<String, String> mergedStyles = new java.util.LinkedHashMap<>(this.styles);
        mergedStyles.putAll(other.getStylesMap());
        return new BaseStyleDefinition(mergedStyles); // Or a more specific type if needed
    }
}
