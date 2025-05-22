package com.brunomnsilva.fluentfxcss.stylers.concrete;

import com.brunomnsilva.fluentfxcss.definitions.NodeStyleDefinition;
import com.brunomnsilva.fluentfxcss.stylers.NodeStyler;

import java.util.Map;

/**
 * A concrete styler for fluently defining CSS styles applicable to any
 * generic JavaFX {@link javafx.scene.Node}.
 * <p>
 * This class extends {@link NodeStyler} and provides methods for common CSS
 * properties such as opacity, effects (e.g., drop shadow), cursor, and visibility.
 * It builds a {@link NodeStyleDefinition}, which is the most general type of style definition.
 * </p>
 * <p>
 * While this styler can be used directly for any {@code Node}, its styling methods
 * are also inherited by more specific stylers like {@link FluentRegionStyler},
 * {@link FluentShapeStyler}, and {@link FluentTextStyler}. It is typically
 * obtained via {@link com.brunomnsilva.fluentfxcss.FluentFxCss#nodeStyle()}.
 * </p>
 * Use chained methods to define styles and then call {@link #build()} to
 * create the {@link NodeStyleDefinition}.
 *
 * @see NodeStyler
 * @see NodeStyleDefinition
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss#nodeStyle()
 * @see javafx.scene.Node
 */
public class FluentNodeStyler extends NodeStyler<FluentNodeStyler, NodeStyleDefinition> {

    /**
     * Constructs a new {@code FluentNodeStyler}.
     * This constructor is typically not called directly by users; instances are
     * obtained via factory methods in {@link com.brunomnsilva.fluentfxcss.FluentFxCss}.
     */
    public FluentNodeStyler() {
        super();
    }

    /**
     * Creates a {@link NodeStyleDefinition} from the collected styles.
     * This method is called by the {@code build()} method in the superclass.
     *
     * @param builtStyles A map of the CSS properties and values collected by this styler.
     * @return A new {@link NodeStyleDefinition} instance.
     */
    @Override
    protected NodeStyleDefinition createDefinition(Map<String, String> builtStyles) {
        return new NodeStyleDefinition(builtStyles);
    }
}
