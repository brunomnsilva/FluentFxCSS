package com.brunomnsilva.fluentfxcss.stylers.concrete;

import com.brunomnsilva.fluentfxcss.definitions.RegionStyleDefinition;
import com.brunomnsilva.fluentfxcss.stylers.RegionStyler;

import java.util.Map;

/**
 * A concrete styler for fluently defining CSS styles for generic
 * JavaFX {@link javafx.scene.layout.Region} nodes.
 * <p>
 * This class extends {@link RegionStyler} and builds a {@link RegionStyleDefinition}.
 * It is typically obtained via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#regionStyle()}.
 * </p>
 * Use chained methods to define region-specific styles (e.g., background, border, padding)
 * and then call {@link #build()} to create the {@link RegionStyleDefinition}.
 *
 * @see RegionStyler
 * @see RegionStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#regionStyle()
 * @see javafx.scene.layout.Region
 *
 * @author brunomnsilva
 */
public class FluentRegionStyler extends RegionStyler<FluentRegionStyler, RegionStyleDefinition> {

    /**
     * Constructs a new {@code FluentRegionStyler}.
     * Typically instantiated via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#regionStyle()}.
     */
    public FluentRegionStyler() {
        super();
    }

    /**
     * Creates a {@link RegionStyleDefinition} from the collected styles.
     * Called by the {@code build()} method in the superclass.
     *
     * @param builtStyles A map of the CSS properties and values for the region.
     * @return A new {@link RegionStyleDefinition} instance.
     */
    @Override
    protected RegionStyleDefinition createDefinition(Map<String, String> builtStyles) {
        return new RegionStyleDefinition(builtStyles);
    }
}
