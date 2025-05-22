package com.brunomnsilva.fluentfxcss.definitions;

import java.util.Map;

/**
 * A {@link StyleDefinition} specifically tailored for styling JavaFX {@link javafx.scene.shape.Shape} nodes.
 * <p>
 * This class extends {@link BaseStyleDefinition} and is typically produced by
 * {@link com.brunomnsilva.fluentfxcss.stylers.ShapeStyler} or its concrete implementations (e.g.,
 * {@code com.brunomnsilva.fluentfxcss.stylers.concrete.FluentShapeStyler}).
 * </p>
 * <p>
 * It encapsulates CSS styles relevant to geometric shapes, such as fill, stroke color,
 * stroke width, and other stroke properties. While it inherits general style definition
 * capabilities from {@code BaseStyleDefinition}, its specific type indicates that the
 * styles are intended for {@code Shape} based elements.
 * </p>
 * In the current version, this class does not add new methods beyond those in
 * {@code BaseStyleDefinition} but serves as a type-specific marker. Future versions
 * could include convenience methods for accessing parsed shape-specific style properties.
 *
 * @see BaseStyleDefinition
 * @see StyleDefinition
 * @see com.brunomnsilva.fluentfxcss.stylers.ShapeStyler
 * @see javafx.scene.shape.Shape
 *
 * @author brunomnsilva
 */
public class TextStyleDefinition extends BaseStyleDefinition {
    public TextStyleDefinition(Map<String, String> styles) {
        super(styles);
    }
}
