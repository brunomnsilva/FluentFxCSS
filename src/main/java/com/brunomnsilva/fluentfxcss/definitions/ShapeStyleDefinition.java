package com.brunomnsilva.fluentfxcss.definitions;

import java.util.Map;

/**
 * A {@link StyleDefinition} specifically tailored for styling JavaFX {@link javafx.scene.shape.Shape} nodes.
 * <p>
 * This class extends {@link NodeStyleDefinition} and is typically produced by
 * {@link com.brunomnsilva.fluentfxcss.stylers.ShapeStyler} or its concrete implementations (e.g.,
 * {@code com.brunomnsilva.fluentfxcss.stylers.concrete.FluentShapeStyler}).
 * </p>
 * <p>
 * It encapsulates CSS styles relevant to geometric shapes, such as fill, stroke color,
 * stroke width, and other stroke properties. While it inherits general style definition
 * capabilities from {@code NodeStyleDefinition}, its specific type indicates that the
 * styles are intended for {@code Shape} based elements.
 * </p>
 * In the current version, this class does not add new methods beyond those in
 * {@code NodeStyleDefinition} but serves as a type-specific marker. Future versions
 * could include convenience methods for accessing parsed shape-specific style properties.
 *
 * @see NodeStyleDefinition
 * @see StyleDefinition
 * @see com.brunomnsilva.fluentfxcss.stylers.ShapeStyler
 * @see javafx.scene.shape.Shape
 *
 * @author brunomnsilva
 */
public class ShapeStyleDefinition extends NodeStyleDefinition {

    /**
     * Constructs a new {@code ShapeStyleDefinition} with the given map of styles.
     * The provided map of styles is typically generated by a {@code ShapeStyler}
     * and contains styles applicable to {@link javafx.scene.shape.Shape} nodes
     * (e.g., -fx-fill, -fx-stroke).
     *
     * @param styles A map where keys are CSS property names and values are their
     *               CSS string representations, relevant for {@code Shape} styling.
     *               This map will be treated as unmodifiable by this instance.
     */
    public ShapeStyleDefinition(Map<String, String> styles) {
        super(styles);
    }
}