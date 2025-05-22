package com.brunomnsilva.fluentfxcss;

import com.brunomnsilva.fluentfxcss.stylers.NodeStyler;
import com.brunomnsilva.fluentfxcss.stylers.concrete.*;

/**
 * Entry point for the FluentFxCss library, providing static factory methods
 * to create styler builders for various JavaFX node types.
 * <p>
 * This utility class allows users to initiate the process of fluently defining
 * CSS styles for JavaFX components. Each factory method returns a specific
 * styler instance (e.g., {@link FluentRegionStyler}, {@link FluentPaneStyler})
 * which can then be used to chain styling method calls.
 * </p>
 * <p>
 * The stylers produced by this class build {@link com.brunomnsilva.fluentfxcss.definitions.StyleDefinition}
 * objects, which encapsulate the CSS styles and can be applied to nodes or
 * converted into CSS class strings.
 * </p>
 * Example usage:
 * <pre>{@code
 * StyleDefinition paneLook = FluentFxCss.paneStyle()
 *     .padding(10)
 *     .backgroundColor(Color.LIGHTBLUE)
 *     .build();
 * paneLook.applyTo(myPane);
 *
 * String textClassCss = FluentFxCss.textStyle()
 *     .fontFamily("Arial")
 *     .fontSize(16)
 *     .toCssClass("body-text");
 * }</pre>
 * This class is final and cannot be instantiated.
 *
 * @see NodeStyler
 * @see com.brunomnsilva.fluentfxcss.definitions.StyleDefinition
 *
 * @author brunomnsilva
 */
public final class FluentFxCss {

    private FluentFxCss() {
        // Utility class, no instantiation.
    }

    /**
     * Creates a new styler for general {@link javafx.scene.Node} nodes.
     *
     * @return A new {@link FluentNodeStyler} instance.
     */
    public static FluentNodeStyler nodeStyle() {
        return new FluentNodeStyler();
    }

    /**
     * Creates a new styler for general {@link javafx.scene.layout.Region} nodes.
     *
     * @return A new {@link FluentRegionStyler} instance.
     */
    public static FluentRegionStyler regionStyle() {
        return new FluentRegionStyler();
    }

    /**
     * Creates a new styler specifically for {@link javafx.scene.layout.Pane} nodes.
     *
     * @return A new {@link FluentPaneStyler} instance.
     */
    public static FluentPaneStyler paneStyle() {
        return new FluentPaneStyler();
    }

    /**
     * Creates a new styler for general {@link javafx.scene.shape.Shape} nodes.
     *
     * @return A new {@link FluentShapeStyler} instance.
     */
    public static FluentShapeStyler shapeStyle() {
        return new FluentShapeStyler();
    }

    /**
     * Creates a new styler specifically for {@link javafx.scene.text.Text} nodes.
     * Note: Ensure the return type matches your concrete Text styler class name,
     * e.g., {@code FluentTextStyler}.
     *
     * @return A new {@code FluentTextStyler} instance (or your specific class name).
     */
    public static FluentTextStyler textStyle() {
        return new FluentTextStyler();
    }
}
