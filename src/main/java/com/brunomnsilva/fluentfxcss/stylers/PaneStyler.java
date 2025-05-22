package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.PaneStyleDefinition;

/**
 * A concrete styler for fluently defining CSS styles specifically for
 * JavaFX {@link javafx.scene.layout.Pane} nodes.
 * <p>
 * This styler extends {@link RegionStyler}, inheriting all styling methods applicable
 * to general {@link javafx.scene.layout.Region}s. It is typically obtained via
 * {@link com.brunomnsilva.fluentfxcss.FluentFxCss#paneStyle()}.
 * </p>
 * <p>
 * Since {@code Pane} itself doesn't introduce any unique CSS-stylable properties
 * beyond those of a {@code Region}, this styler primarily offers type specificity.
 * It builds a {@link PaneStyleDefinition}, clearly indicating that the defined
 * styles were intended for a {@code Pane}.
 * </p>
 * Use the chained methods to define styles and then call {@link #build()} to
 * obtain a {@link PaneStyleDefinition}.
 *
 * @see RegionStyler
 * @see PaneStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#paneStyle()
 * @see javafx.scene.layout.Pane
 *
 * @author brunomnsilva
 */
public abstract class PaneStyler extends RegionStyler<PaneStyler, PaneStyleDefinition> {

    /**
     * Constructs a new {@code PaneStyler}.
     * This constructor is typically not called directly by users; instances are
     * obtained via factory methods in {@link com.brunomnsilva.fluentfxcss.FluentFxCss}.
     */
    public PaneStyler() {
        super();
    }
}
