package com.brunomnsilva.fluentfxcss.definitions;

import javafx.scene.Node;
import java.util.Map;

/**
 * Represents an immutable definition of a set of CSS styles.
 * <p>
 * A {@code StyleDefinition} is typically created by a styler builder (e.g.,
 * from {@link com.brunomnsilva.fluentfxcss.FluentFxCss}). Once built, it encapsulates
 * a collection of CSS property-value pairs.
 * </p>
 * <p>
 * This definition can then be used to:
 * <ul>
 *     <li>Apply the styles directly to a JavaFX {@link Node} as an inline style.</li>
 *     <li>Generate a CSS string suitable for inline styling.</li>
 *     <li>Generate CSS rule strings for inclusion in a stylesheet (e.g., as a CSS class).</li>
 *     <li>Be merged with other {@code StyleDefinition} objects.</li>
 * </ul>
 * </p>
 * Implementations are expected to be immutable or effectively immutable after creation.
 *
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss
 * @see com.brunomnsilva.fluentfxcss.stylers.BaseStyler#build()
 *
 * @author brunomnsilva
 */
public interface StyleDefinition {

    /**
     * Applies the defined styles as an inline style to the given JavaFX {@link Node}.
     * This is equivalent to calling {@code node.setStyle(toCssInline())}.
     *
     * @param node The Node to which the styles will be applied. Must not be null.
     * @param <T>  The type of the Node being styled.
     * @return The styled Node, allowing for method chaining on the Node if desired.
     * @throws NullPointerException if the node is null.
     */
    <T extends Node> T applyTo(T node);

    /**
     * Applies the defined styles as an inline style to one or more JavaFX {@link Node}s.
     * This iterates through the provided nodes and calls {@link #applyTo(Node)} on each.
     *
     * @param nodes Varargs array of Nodes to which the styles will be applied.
     *              If the array is null or empty, the method does nothing.
     *              Individual null nodes within the array will be skipped.
     * @param <T>   The type of the Nodes being styled.
     * @throws ClassCastException if nodes within the varargs array are not of a compatible type T
     *                            (though typically T will be Node, making this rare).
     */
    @SuppressWarnings("unchecked") // For the varargs array
    <T extends Node> void applyTo(T... nodes);

    /**
     * Creates a new {@code StyleDefinition} by merging the styles from this definition
     * with the styles from another {@code StyleDefinition}.
     * <p>
     * If both definitions contain the same CSS property, the property from the
     * {@code other} StyleDefinition will take precedence in the resulting merged definition.
     * The order of properties from the original definitions is generally preserved for
     * non-conflicting properties.
     * </p>
     *
     * @param other The {@code StyleDefinition} to merge with this one. Must not be null.
     * @return A new {@code StyleDefinition} containing the merged styles.
     * @throws NullPointerException if other is null.
     */
    StyleDefinition mergeWith(StyleDefinition other);

    /**
     * Retrieves the complete CSS string representing all defined styles.
     * This string is suitable for direct use with {@code node.setStyle()}.
     * Example: {@code "-fx-background-color: #FF0000FF; -fx-padding: 10.00px;"}
     *
     * @return A string containing all CSS property-value pairs, semicolon-separated.
     *         Returns an empty string if no styles are defined.
     */
    String toCssInline();

    /**
     * Converts this style definition into a complete CSS rule string using the provided selector.
     * This is the most flexible method for generating CSS rules.
     * <p>
     * Example: {@code toCssRule(".my-custom-element:hover")} would produce:
     * <pre>{@code
     * .my-custom-element:hover {
     *     -fx-property1: value1;
     *     -fx-property2: value2;
     * }
     * }</pre>
     * </p>
     *
     * @param selector The full CSS selector (e.g., ".my-class", "#my-id:active", "VBox > .child-class").
     *                 The caller is responsible for providing a syntactically valid CSS selector.
     *                 No syntax validation is performed.
     * @return A string representing the complete CSS rule. Returns an empty string or a
     *         rule with an empty body if no styles are defined.
     * @throws IllegalArgumentException if the selector is null or empty.
     */
    String toCssRule(String selector);

    /**
     * Converts this style definition into a CSS class rule string.
     * This is a convenience method equivalent to calling
     * {@code toCssRule("." + className)}.
     *
     * @param className The name of the CSS class (e.g., "my-button"). The leading dot is added automatically.
     * @return A string representing the CSS class rule (e.g., ".my-button { ... }").
     * @throws IllegalArgumentException if the className is null or empty.
     * @see #toCssRule(String)
     */
    String toCssClass(String className);

    /**
     * Converts this style definition into a CSS pseudo-class rule string, applied to a given base class.
     * This is a convenience method equivalent to calling
     * {@code toCssRule("." + baseClassName + ":" + pseudoClass)}.
     *
     * @param baseClassName The name of the base CSS class (e.g., "my-button"). The leading dot is added automatically.
     * @param pseudoClass   The pseudo-class excluding the leading colon (e.g., "hover", "focus", "disabled").
     * @return A string representing the CSS pseudo-class rule (e.g., ".my-button:hover { ... }").
     * @throws IllegalArgumentException if baseClassName or pseudoClass is invalid (e.g., null or empty).
     * @see #toCssRule(String)
     */
    String toCssPseudoClass(String baseClassName, String pseudoClass);

    /**
     * Gets the underlying map of CSS properties and values.
     * Primarily for internal use or advanced scenarios like merging.
     * @return An unmodifiable map of styles.
     */
    Map<String, String> getStylesMap();
}
