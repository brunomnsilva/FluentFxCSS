package com.brunomnsilva.fluentfxcss.definitions;

import com.brunomnsilva.fluentfxcss.util.Args;
import javafx.scene.Node;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A base concrete implementation of the {@link StyleDefinition} interface.
 * <p>
 * This class provides the fundamental functionality for a style definition, including
 * storing CSS property-value pairs and implementing methods for applying these styles
 * as inline styles or generating CSS rule strings.
 * </p>
 * <p>
 * Instances of this class (or its subclasses) are typically created by the
 * {@code build()} method of styler builders (e.g., from {@link com.brunomnsilva.fluentfxcss.stylers.BaseStyler}).
 * The map of styles provided during construction is treated as immutable or copied to ensure
 * the {@code BaseStyleDefinition} itself remains effectively immutable after creation.
 * </p>
 * This class is intended to be extended by more specific style definition types
 * (e.g., {@code RegionStyleDefinition}, {@code TextStyleDefinition}) if specialized behavior
 * or typing is required for those definitions, though it can also be used directly.
 *
 * @see StyleDefinition
 * @see com.brunomnsilva.fluentfxcss.stylers.BaseStyler
 *
 * @author brunomnsilva
 */
public class BaseStyleDefinition implements StyleDefinition {
    protected final Map<String, String> styles; // Should be immutable or a copy

    public BaseStyleDefinition(Map<String, String> styles) {
        // Ensure immutability
        this.styles = Collections.unmodifiableMap(styles);
    }

    @Override
    public <T extends Node> T applyTo(T node) {
        if (node != null) {
            node.setStyle(toCssInline());
        }
        return node;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> void applyTo(T... nodes) {
        if (nodes == null || nodes.length == 0) {
            return;
        }

        String cssStringToApply = toCssInline();
        if (cssStringToApply.isEmpty()) {
            return; // Nothing to apply
        }

        for (T node : nodes) {
            if (node != null) {
                node.setStyle(cssStringToApply);
            }
        }
    }


    @Override
    public String toCssInline() {
        return styles.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + ";")
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toCssRule(String selector) {
        Args.requireNotNull(selector, "selector");
        Args.requireNotEmpty(selector, "selector");

        StringBuilder sb = new StringBuilder();
        sb.append(selector.trim()).append(" {\n");
        styles.forEach((property, value) -> {
            sb.append("    ").append(property).append(": ").append(value).append(";\n");
        });
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public String toCssClass(String className) {
        Args.requireNotNull(className, "className");
        Args.requireNotEmpty(className, "className");
        Args.requireMatches(className, "^[a-zA-Z_][a-zA-Z0-9_-]*$", "className");

        return toCssRule("." + className.trim());
    }

    @Override
    public String toCssPseudoClass(String baseClassName, String pseudoClass) {
        Args.requireNotNull(baseClassName, "baseClassName");
        Args.requireNotEmpty(baseClassName, "baseClassName");
        Args.requireMatches(baseClassName, "^[a-zA-Z_][a-zA-Z0-9_-]*$", "baseClassName");

        Args.requireNotNull(pseudoClass, "pseudoClass");
        Args.requireNotEmpty(pseudoClass, "pseudoClass");

        return toCssRule("." + baseClassName.trim() + ":" + pseudoClass.trim()); // Delegates
    }


    @Override
    public Map<String, String> getStylesMap() {
        return styles;
    }


    @Override
    public StyleDefinition mergeWith(StyleDefinition other) {
        Map<String, String> mergedStyles = new java.util.LinkedHashMap<>(this.styles);
        mergedStyles.putAll(other.getStylesMap());
        return new BaseStyleDefinition(mergedStyles);
    }

    @Override
    public String toString() {
        return toCssInline();
    }
}
