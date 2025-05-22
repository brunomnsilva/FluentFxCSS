package com.brunomnsilva.fluentfxcss.stylers.concrete;

import com.brunomnsilva.fluentfxcss.definitions.TextStyleDefinition;
import com.brunomnsilva.fluentfxcss.stylers.TextStyler;

import java.util.Map;

/**
 * A concrete styler for fluently defining CSS styles for generic
 * JavaFX {@link javafx.scene.text.Text} nodes.
 * <p>
 * This class extends {@link TextStyler} and builds a {@link TextStyleDefinition}.
 * It is typically obtained via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#textStyle()}.
 * </p>
 * Use chained methods to define text-specific styles (e.g., font, weight, underline)
 * and then call {@link #build()} to create the {@link TextStyleDefinition}.
 *
 * @see TextStyler
 * @see TextStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#textStyle()
 * @see javafx.scene.text.Text
 *
 * @author brunomnsilva
 */
public class FluentTextStyler extends TextStyler<FluentTextStyler, TextStyleDefinition> {

    /**
     * Constructs a new {@code FluentTextStyler}.
     * Typically instantiated via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#textStyle()}.
     */
    public FluentTextStyler() {
        super();
    }

    /**
     * Creates a {@link TextStyleDefinition} from the collected styles.
     * Called by the {@code build()} method in the superclass.
     *
     * @param builtStyles A map of the CSS properties and values for the region.
     * @return A new {@link TextStyleDefinition} instance.
     */
    @Override
    protected TextStyleDefinition createDefinition(Map<String, String> builtStyles) {
        return new TextStyleDefinition(builtStyles);
    }

}
