package com.brunomnsilva.fluentfxcss.stylers.concrete;

import com.brunomnsilva.fluentfxcss.definitions.ShapeStyleDefinition;
import com.brunomnsilva.fluentfxcss.stylers.ShapeStyler;

import java.util.Map;

/**
 * A concrete styler for fluently defining CSS styles for generic
 * JavaFX {@link javafx.scene.shape.Shape} nodes.
 * <p>
 * This class extends {@link ShapeStyler} and builds a {@link ShapeStyleDefinition}.
 * It is typically obtained via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#shapeStyle()}.
 * </p>
 * Use chained methods to define shape-specific styles (e.g., fill, stroke)
 * and then call {@link #build()} to create the {@link ShapeStyleDefinition}.
 *
 * @see ShapeStyler
 * @see ShapeStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#shapeStyle()
 * @see javafx.scene.shape.Shape
 *
 * @author brunomnsilva
 */
public class FluentShapeStyler extends ShapeStyler<FluentShapeStyler, ShapeStyleDefinition> { // Pass itself as S

    /**
     * Constructs a new {@code FluentShapeStyler}.
     * Typically instantiated via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#shapeStyle()}.
     */
    public FluentShapeStyler() {
        super();
    }

    /**
     * Creates a {@link ShapeStyleDefinition} from the collected styles.
     * Called by the {@code build()} method in the superclass.
     *
     * @param builtStyles A map of the CSS properties and values for the region.
     * @return A new {@link ShapeStyleDefinition} instance.
     */
    @Override
    protected ShapeStyleDefinition createDefinition(Map<String, String> builtStyles) {
        return new ShapeStyleDefinition(builtStyles);
    }
}
