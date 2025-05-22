package com.brunomnsilva.fluentfxcss.stylers.concrete;

import com.brunomnsilva.fluentfxcss.definitions.PaneStyleDefinition;
import com.brunomnsilva.fluentfxcss.stylers.PaneStyler;

import java.util.Map;

/**
 * A concrete styler for fluently defining CSS styles for generic
 * JavaFX {@link javafx.scene.layout.Pane} nodes.
 * <p>
 * This class extends {@link PaneStyler} and builds a {@link PaneStyleDefinition}.
 * It is typically obtained via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#regionStyle()}.
 * </p>
 * Use chained methods to define pane-specific styles (e.g., background, border, padding)
 * and then call {@link #build()} to create the {@link PaneStyleDefinition}.
 *
 * @see PaneStyler
 * @see PaneStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#paneStyle()
 * @see javafx.scene.layout.Region
 *
 * @author brunomnsilva
 */
public class FluentPaneStyler extends PaneStyler {

    /**
     * Constructs a new {@code FluentPaneStyler}.
     * Typically instantiated via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#paneStyle()}.
     */
    public FluentPaneStyler() {
        super();
    }

    /**
     * Creates a {@link PaneStyleDefinition} from the collected styles.
     * Called by the {@code build()} method in the superclass.
     *
     * @param builtStyles A map of the CSS properties and values for the region.
     * @return A new {@link PaneStyleDefinition} instance.
     */
    @Override
    protected PaneStyleDefinition createDefinition(Map<String, String> builtStyles) {
        return new PaneStyleDefinition(builtStyles);
    }
}
