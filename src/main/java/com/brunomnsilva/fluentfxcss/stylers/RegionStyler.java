package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.BorderStyleValue;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import com.brunomnsilva.fluentfxcss.util.Args;
import com.brunomnsilva.fluentfxcss.util.CssHelper;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.SVGPath;

/**
 * An abstract styler for defining CSS properties specific to JavaFX {@link Region} nodes
 * and their descendants (e.g., {@link javafx.scene.layout.Pane}, {@link javafx.scene.control.Control}).
 * <p>
 * This class extends {@link NodeStyler} and adds fluent methods for common
 * region-related styles such as background, border, and padding.
 * Subclasses, like concrete stylers for specific Region types (e.g., {@code FluentPaneStyler}),
 * will extend this class.
 * </p>
 * <p>
 * Many methods offer overloads that accept a {@link UnitValue} to specify units
 * (e.g., PX, EM, PERCENT) for length-based properties. If no unit is specified,
 * pixels ({@link UnitValue#PX}) are typically assumed.
 * </p>
 *
 * @param <S> The concrete type of the styler extending this class (CRTP).
 * @param <D> The specific type of {@link StyleDefinition} this styler builds.
 * @see NodeStyler
 * @see StyleDefinition
 * @see javafx.scene.layout.Region
 *
 * @author brunomnsilva
 */
public abstract class RegionStyler<S extends RegionStyler<S, D>, D extends StyleDefinition>
        extends NodeStyler<S, D> {

    /**
     * Protected constructor for subclasses.
     * Initializes the styler which is a descendant of {@link NodeStyler}.
     */
    protected RegionStyler() {
        super();
    }

    // -- Background-related properties

    /**
     * Converts a JavaFX {@link Paint} object to its corresponding CSS string representation.
     * This method handles:
     * <ul>
     *     <li>{@link Color}: Converts to a CSS color string (e.g., "#RRGGBBAA", "rgba(...)").</li>
     *     <li>{@link LinearGradient}: Converts to a CSS {@code linear-gradient(...)} string.</li>
     *     <li>{@link RadialGradient}: Converts to a CSS {@code radial-gradient(...)} string.</li>
     * </ul>
     * If the provided {@code paint} is null, it defaults to "transparent".
     * For other {@code Paint} subtypes not explicitly handled, it falls back to calling
     * {@code paint.toString()}, which may or may not produce a valid CSS value.
     *
     * @param paint The {@link Paint} object to convert (e.g., Color, LinearGradient, RadialGradient).
     *              Can be null.
     * @return The CSS string representation of the paint (e.g., "#FF0000FF",
     *         "linear-gradient(from 0.0px 0.0px to 100.0px 0.0px, red 0.0%, blue 100.0%)",
     *         "transparent"). Returns "transparent" if paint is null.
     *         The output for unhandled Paint types is {@code paint.toString()}.
     *
     * @see CssHelper#toCssPaint(Paint) (Color)
     * @see CssHelper#toCssLinearGradient(LinearGradient)
     * @see CssHelper#toCssRadialGradient(RadialGradient)
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typecolor">JavaFX CSS Color</a>
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-color with gradients)</a>
     */
    public S backgroundColor(Paint paint) {
        addStyle("-fx-background-color", CssHelper.toCssPaint(paint));
        return self();
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property for all four sides with a single value,
     * assuming pixel units.
     * <p>
     * Background insets define the distance from the edge of the region to the edge
     * of the background. This is particularly useful when using multiple backgrounds
     * to create layered effects or when you want the background to be smaller than the region.
     * </p>
     *
     * @param allSides The inset value for all sides (top, right, bottom, left) in pixels.
     * @return This styler instance for chaining.
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-background-insets)</a>
     */
    public S backgroundInsets(double allSides) {
        return backgroundInsets(UnitValue.PX, allSides);
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property for all four sides with a single value
     * and a specified unit.
     *
     * @param unit     The unit for the inset value.
     * @param allSides The inset value for all sides.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if unit is null.
     */
    public S backgroundInsets(UnitValue unit, double allSides) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-background-insets", CssHelper.toDoubleString(unit, allSides));
        return self();
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property with two values, assuming pixel units.
     * The first value applies to top/bottom, the second to left/right.
     *
     * @param topBottom The inset for top and bottom sides in pixels.
     * @param leftRight The inset for left and right sides in pixels.
     * @return This styler instance for chaining.
     */
    public S backgroundInsets(double topBottom, double leftRight) {
        return backgroundInsets(UnitValue.PX, topBottom, leftRight);
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property with two values and a specified unit.
     * The first value applies to top/bottom, the second to left/right.
     *
     * @param unit      The unit for the inset values.
     * @param topBottom The inset for top and bottom sides.
     * @param leftRight The inset for left and right sides.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if unit is null.
     */
    public S backgroundInsets(UnitValue unit, double topBottom, double leftRight) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-background-insets", CssHelper.toDoubleArrayString(unit, topBottom, leftRight));
        return self();
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property with four values (top, right, bottom, left),
     * assuming pixel units.
     *
     * @param top    The inset for the top side in pixels.
     * @param right  The inset for the right side in pixels.
     * @param bottom The inset for the bottom side in pixels.
     * @param left   The inset for the left side in pixels.
     * @return This styler instance for chaining.
     */
    public S backgroundInsets(double top, double right, double bottom, double left) {
        return backgroundInsets(UnitValue.PX, top, right, bottom, left);
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property with four values (top, right, bottom, left)
     * and a specified unit.
     *
     * @param unit   The unit for the inset values.
     * @param top    The inset for the top side.
     * @param right  The inset for the right side.
     * @param bottom The inset for the bottom side.
     * @param left   The inset for the left side.
     * @return This styler instance for chaining.
     */
    public S backgroundInsets(UnitValue unit, double top, double right, double bottom, double left) {
        addStyle("-fx-background-insets", CssHelper.toDoubleArrayString(unit, top, right, bottom, left));
        return self();
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property using a JavaFX {@link Insets} object,
     * assuming pixel units.
     *
     * @param insets The {@link Insets} object defining the background insets. Must not be null.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if insets is null.
     */
    public S backgroundInsets(Insets insets) {
        Args.requireNotNull(insets, "insets");
        return backgroundInsets(UnitValue.PX, insets.getTop(), insets.getRight(), insets.getBottom(), insets.getLeft());
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property using a JavaFX {@link Insets} object
     * with a specified unit.
     *
     * @param unit   The unit for the inset values derived from the {@code Insets} object.
     * @param insets The {@link Insets} object defining the background insets. Must not be null.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if insets is null or unit is null.
     */
    public S backgroundInsets(UnitValue unit, Insets insets) {
        Args.requireNotNull(unit, "unit");
        Args.requireNotNull(insets, "insets");
        addStyle("-fx-background-insets", CssHelper.toDoubleArrayString(unit, insets.getTop(), insets.getRight(), insets.getBottom(), insets.getLeft()));
        return self();
    }

    /**
     * Sets the {@code -fx-background-insets} CSS property to {@code inherit}.
     * This causes the region to inherit the background insets from its parent.
     *
     * @return This styler instance for chaining.
     */
    public S backgroundInsetsInherit() {
        addStyle("-fx-background-insets", "inherit");
        return self();
    }

    /**
     * Sets the {@code -fx-background-radius} CSS property for all corners, assuming pixel units.
     * This defines the radii of the corners of the background.
     *
     * @param radius The radius for all corners in pixels.
     * @return This styler instance for chaining.
     */
    public S backgroundRadius(double radius) {
        return backgroundRadius(UnitValue.PX, radius);
    }

    /**
     * Sets the {@code -fx-background-radius} CSS property for all corners with a specified unit.
     * This defines the radii of the corners of the background.
     *
     * @param unit   The unit for the radius value.
     * @param radius The radius for all corners.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S backgroundRadius(UnitValue unit, double radius) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-background-radius", CssHelper.toDoubleString(unit, radius));
        return self();
    }

    /**
     * Sets the {@code -fx-background-radius} CSS property for each corner individually, assuming pixel units.
     * Values are for top-left, top-right, bottom-right, bottom-left corners respectively.
     *
     * @param topLeft     The radius for the top-left corner in pixels.
     * @param topRight    The radius for the top-right corner in pixels.
     * @param bottomRight The radius for the bottom-right corner in pixels.
     * @param bottomLeft  The radius for the bottom-left corner in pixels.
     * @return This styler instance for chaining.
     */
    public S backgroundRadius(double topLeft, double topRight, double bottomRight, double bottomLeft) {
        return backgroundRadius(UnitValue.PX, topLeft, topRight, bottomRight, bottomLeft);
    }

    /**
     * Sets the {@code -fx-background-radius} CSS property for each corner individually with a specified unit.
     * Values are for top-left, top-right, bottom-right, bottom-left corners respectively.
     *
     * @param unit        The unit for the radius values.
     * @param topLeft     The radius for the top-left corner.
     * @param topRight    The radius for the top-right corner.
     * @param bottomRight The radius for the bottom-right corner.
     * @param bottomLeft  The radius for the bottom-left corner.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S backgroundRadius(UnitValue unit, double topLeft, double topRight, double bottomRight, double bottomLeft) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-background-radius", CssHelper.toDoubleArrayString(unit, topLeft, topRight, bottomRight, bottomLeft));
        return self();
    }

    /**
     * Sets the {@code -fx-border-color} CSS property for all sides of the border.
     *
     * @param color The color of the border.
     * @return This styler instance for chaining.
     */
    public S borderColor(Color color) {
        // TODO: JavaFX CSS -fx-border-color can take 1 to 4 color values.
        // This method sets a single color for all sides. Overloads could be added for more.
        addStyle("-fx-border-color", CssHelper.toCssColor(color));
        return self();
    }

    /**
     * Sets the {@code -fx-border-style} CSS property for all sides of the border.
     *
     * @param style The style of the border (e.g., SOLID, DASHED).
     * @return This styler instance for chaining.
     * @see BorderStyleValue
     */
    public S borderStyle(BorderStyleValue style) {
        // TODO: JavaFX CSS -fx-border-style can take 1 to 4 style values.
        // This method sets a single style for all sides. Overloads should exist for more.
        addStyle("-fx-border-style", CssHelper.toCssBorderStyle(style)); // Or direct style.getCssValue()
        return self();
    }

    /**
     * Sets the {@code -fx-border-width} CSS property for all sides, assuming pixel units.
     *
     * @param width The width of the border in pixels.
     * @return This styler instance for chaining.
     */
    public S borderWidth(double width) {
        return borderWidth(UnitValue.PX, width);
    }

    /**
     * Sets the {@code -fx-border-width} CSS property for all sides with a specified unit.
     *
     * @param unit  The unit for the width value.
     * @param width The width of the border.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S borderWidth(UnitValue unit, double width) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-border-width", CssHelper.toDoubleString(unit, width));
        return self();
    }

    /**
     * Sets the {@code -fx-border-width} CSS property for each side individually, assuming pixel units.
     * Values are for top, right, bottom, left sides respectively.
     *
     * @param topLeft     The width for the top border in pixels.
     * @param topRight    The width for the right border in pixels.
     * @param bottomRight The width for the bottom border in pixels.
     * @param bottomLeft  The width for the left border in pixels.
     * @return This styler instance for chaining.
     */
    public S borderWidth(double topLeft, double topRight, double bottomRight, double bottomLeft) {
        return borderWidth(UnitValue.PX, topLeft, topRight, bottomRight, bottomLeft);
    }

    /**
     * Sets the {@code -fx-border-width} CSS property for each side individually with a specified unit.
     * Values are for top, right, bottom, left sides respectively.
     * <p>
     * Note: The original code for this method incorrectly sets {@code -fx-border-radius}.
     * This Javadoc assumes it's intended for {@code -fx-border-width}.
     * </p>
     *
     * @param unit        The unit for the width values.
     * @param topLeft     The width for the top border.
     * @param topRight    The width for the right border.
     * @param bottomRight The width for the bottom border.
     * @param bottomLeft  The width for the left border.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S borderWidth(UnitValue unit, double topLeft, double topRight, double bottomRight, double bottomLeft) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-border-width", CssHelper.toDoubleArrayString(unit, topLeft, topRight, bottomRight, bottomLeft));
        return self();
    }


    /**
     * Sets the {@code -fx-border-radius} CSS property for all corners, assuming pixel units.
     * This defines the radii of the corners of the border.
     *
     * @param radius The radius for all corners in pixels.
     * @return This styler instance for chaining.
     */
    public S borderRadius(double radius) {
        return borderRadius(UnitValue.PX, radius);
    }

    /**
     * Sets the {@code -fx-border-radius} CSS property for all corners with a specified unit.
     * This defines the radii of the corners of the border.
     *
     * @param unit   The unit for the radius value.
     * @param radius The radius for all corners.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S borderRadius(UnitValue unit, double radius) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-border-radius", CssHelper.toDoubleString(unit, radius));
        return self();
    }

    /**
     * Sets the {@code -fx-border-radius} CSS property for each corner individually, assuming pixel units.
     * Values are for top-left, top-right, bottom-right, bottom-left corners respectively.
     *
     * @param topLeft     The radius for the top-left corner in pixels.
     * @param topRight    The radius for the top-right corner in pixels.
     * @param bottomRight The radius for the bottom-right corner in pixels.
     * @param bottomLeft  The radius for the bottom-left corner in pixels.
     * @return This styler instance for chaining.
     */
    public S borderRadius(double topLeft, double topRight, double bottomRight, double bottomLeft) {
        return borderRadius(UnitValue.PX, topLeft, topRight, bottomRight, bottomLeft);
    }

    /**
     * Sets the {@code -fx-border-radius} CSS property for each corner individually with a specified unit.
     * Values are for top-left, top-right, bottom-right, bottom-left corners respectively.
     *
     * @param unit        The unit for the radius values.
     * @param topLeft     The radius for the top-left corner.
     * @param topRight    The radius for the top-right corner.
     * @param bottomRight The radius for the bottom-right corner.
     * @param bottomLeft  The radius for the bottom-left corner.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S borderRadius(UnitValue unit, double topLeft, double topRight, double bottomRight, double bottomLeft) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-border-radius", CssHelper.toDoubleArrayString(unit, topLeft, topRight, bottomRight, bottomLeft));
        return self();
    }

    /**
     * Sets the {@code -fx-padding} CSS property for all four sides, assuming pixel units.
     *
     * @param allSides The padding value for all sides in pixels.
     * @return This styler instance for chaining.
     */
    public S padding(double allSides) {
        return padding(UnitValue.PX, allSides);
    }

    /**
     * Sets the {@code -fx-padding} CSS property for all four sides with a specified unit.
     *
     * @param unit     The unit for the padding value.
     * @param allSides The padding value for all sides.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S padding(UnitValue unit, double allSides) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-padding", CssHelper.toDoubleString(unit, allSides));
        return self();
    }

    /**
     * Sets the {@code -fx-padding} CSS property with two values, assuming pixel units.
     * The first value applies to top/bottom, the second to left/right.
     *
     * @param topBottom The padding for top and bottom in pixels.
     * @param leftRight The padding for left and right in pixels.
     * @return This styler instance for chaining.
     */
    public S padding(double topBottom, double leftRight) {
        return padding(UnitValue.PX, topBottom, leftRight);
    }

    /**
     * Sets the {@code -fx-padding} CSS property with two values and a specified unit.
     * The first value applies to top/bottom, the second to left/right.
     *
     * @param unit      The unit for the padding values.
     * @param topBottom The padding for top and bottom.
     * @param leftRight The padding for left and right.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S padding(UnitValue unit, double topBottom, double leftRight) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-padding", CssHelper.toDoubleArrayString(unit, topBottom, leftRight));
        return self();
    }

    /**
     * Sets the {@code -fx-padding} CSS property with four values (top, right, bottom, left), assuming pixel units.
     *
     * @param top    The padding for the top side in pixels.
     * @param right  The padding for the right side in pixels.
     * @param bottom The padding for the bottom side in pixels.
     * @param left   The padding for the left side in pixels.
     * @return This styler instance for chaining.
     */
    public S padding(double top, double right, double bottom, double left) {
        return padding(UnitValue.PX, top, right, bottom, left);
    }

    /**
     * Sets the {@code -fx-padding} CSS property with four values (top, right, bottom, left) and a specified unit.
     *
     * @param unit   The unit for the padding values. If {@code null}, "px" is used.
     * @param top    The padding for the top side.
     * @param right  The padding for the right side.
     * @param bottom The padding for the bottom side.
     * @param left   The padding for the left side.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S padding(UnitValue unit, double top, double right, double bottom, double left) {
        Args.requireNotNull(unit, "unit");
        addStyle("-fx-padding", CssHelper.toDoubleArrayString(unit, top, right, bottom, left));
        return self();
    }

    /**
     * Sets the {@code -fx-padding} CSS property using an {@link Insets} object, assuming pixel units.
     *
     * @param insets The {@link Insets} object defining the padding. Must not be null.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if insets is null.
     */
    public S padding(Insets insets) {
        return padding(UnitValue.PX, insets);
    }

    /**
     * Sets the {@code -fx-padding} CSS property using an {@link Insets} object with a specified unit.
     *
     * @param unit   The unit for the padding values derived from the insets. If {@code null}, "px" is used.
     * @param insets The {@link Insets} object defining the padding. Must not be null.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}
     */
    public S padding(UnitValue unit, Insets insets) {
        Args.requireNotNull(unit, "unit");

        addStyle("-fx-padding", CssHelper.toDoubleArrayString(unit, insets.getTop(), insets.getRight(), insets.getBottom(), insets.getLeft()));
        return self();
    }

    // -- -fx-shape related properties

    /**
     * Sets the {@code -fx-shape} CSS property, defining an SVG path string that
     * is used to clip the region's background and border.
     * <p>
     * The shape is specified as a string that conforms to the
     * <a href="http://www.w3.org/TR/SVG/paths.html#PathData">SVG path format</a>.
     * This allows for creating non-rectangular regions. The coordinates in the SVG path
     * are relative to the region's top-left corner (0,0).
     * </p>
     * Example of an SVG path for a simple rounded rectangle (though {@code -fx-background-radius}
     * and {@code -fx-border-radius} are usually preferred for this specific case):
     * <pre>{@code "M0,10 A10,10 0 0 1 10,0 L90,0 A10,10 0 0 1 100,10 L100,90 A10,10 0 0 1 90,100 L10,100 A10,10 0 0 1 0,90 Z"}</pre>
     * For a circle with radius 50 at center (50,50) of a 100x100 region:
     * <pre>{@code "M50,0 A50,50 0 1 1 49.9,0 Z"}</pre> (Approximation due to SVG arc rendering quirks)
     * Or more simply for a circle shape, if the region's size is known and matches the desired circle diameter:
     * <pre>{@code "M 0,50 A 50,50 0 1 1 100,50 A 50,50 0 1 1 0,50 Z"} assuming a 100x50 region to make a half circle effect</pre>
     * A proper circle fitting a 100x100 region would be:
     * <pre>{@code "M 0,50 C 0,22.38 22.38,0 50,0 C 77.62,0 100,22.38 100,50 C 100,77.62 77.62,100 50,100 C 22.38,100 0,77.62 0,50 Z"}</pre>
     * If the provided SVG path string is null or empty, the {@code -fx-shape} property
     * will be set to {@code "null"}.
     *
     * @param svgPath The SVG path string defining the shape. Can be null or empty.
     *                If null or effectively empty, the property will be set to {@code "null"}.
     * @return This styler instance for chaining.
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-shape)</a>
     * @see <a href="http://www.w3.org/TR/SVG/paths.html#PathData">SVG PathData Specification</a>
     */
    public S shape(String svgPath) {
        if(svgPath == null || svgPath.trim().isEmpty()) {
            svgPath = "null";
        }

        addStyle("-fx-shape", "\"" + svgPath.trim() + "\"");
        return self();
    }

    /**
     * Sets the {@code -fx-shape} CSS property, defining an SVG path that
     * is used to clip the region's background and border.
     *
     * @param svgPath the {@code SVGPath} instance to use
     * @return This styler instance for chaining.
     * @see <a href="https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/shape/SVGPath.html">SVGPath Javadoc</a>
     */
    public S shape(SVGPath svgPath) {
        addStyle("-fx-shape", CssHelper.toCssSvgPath(svgPath));
        return self();
    }

    /**
     * Sets the {@code -fx-scale-shape} CSS property.
     * <p>
     * This property controls whether the defined {@code -fx-shape} is scaled to fit
     * the size of the region.
     * If {@code true} (the default), the shape is scaled to fit the region's bounds.
     * If {@code false}, the shape is rendered at its natural size as defined by the SVG path.
     * </p>
     * This property only has an effect if {@code -fx-shape} is also set.
     *
     * @param scaleToFit {@code true} to scale the shape to the region's size,
     *                   {@code false} to use the shape's natural size.
     * @return This styler instance for chaining.
     * @see #shape(String)
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-scale-shape)</a>
     */
    public S scaleShape(boolean scaleToFit) {
        addStyle("-fx-scale-shape", String.valueOf(scaleToFit));
        return self();
    }

    /**
     * Sets the {@code -fx-position-shape} CSS property.
     * <p>
     * This property controls whether the defined {@code -fx-shape} is positioned
     * relative to the region's top-left corner or relative to its center.
     * If {@code true}, the shape is positioned relative to the center of the region.
     * If {@code false} (the default), the shape is positioned relative to the top-left
     * corner of the region.
     * </p>
     * This property only has an effect if {@code -fx-shape} is also set and
     * {@code -fx-scale-shape} is {@code false} (i.e., the shape is not being scaled to fit).
     * If the shape is scaled to fit, its position is inherently determined by the scaling.
     *
     * @param centerPositioned {@code true} to position the shape relative to the region's center,
     *                         {@code false} to position relative to the top-left corner.
     * @return This styler instance for chaining.
     * @see #shape(String)
     * @see #scaleShape(boolean)
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#region">JavaFX CSS Region (-fx-position-shape)</a>
     */
    public S positionShape(boolean centerPositioned) {
        addStyle("-fx-position-shape", String.valueOf(centerPositioned));
        return self();
    }

}