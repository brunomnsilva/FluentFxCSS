package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.BackgroundPositionValue;
import com.brunomnsilva.fluentfxcss.enums.BackgroundRepeatValue;
import com.brunomnsilva.fluentfxcss.enums.BackgroundSizeValue;
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
     * Sets the {@code -fx-background-image} CSS property.
     * Specifies the URL of the background image.
     *
     * @param url The URL of the background image. If null or blank, "none" will be used.
     * @return This styler instance for chaining.
     */
    public S backgroundImage(String url) {
        addStyle("-fx-background-image", CssHelper.toCssUrl(url));
        return self();
    }

    /**
     * Sets the {@code -fx-background-position} CSS property using predefined enum values.
     * Defines the initial position of the background image.
     *
     * @param position A {@link BackgroundPositionValue} enum constant (e.g., {@code BackgroundPositionValue.CENTER_CENTER}). Must not be null.
     * @return This styler instance for chaining.
     */
    public S backgroundPosition(BackgroundPositionValue position) {
        Args.requireNotNull(position, "position");
        addStyle("-fx-background-position", position.getCssValue());
        return self();
    }

    /**
     * Sets the {@code -fx-background-position} CSS property using a custom string value.
     * Defines the initial position of the background image. This allows for values
     * not covered by the {@link BackgroundPositionValue} enum, such as specific
     * percentages or length units (e.g., "50% 50%", "10px 20px").
     *
     * @param position The CSS string for the background position (e.g., "center", "top left", "25% 75%"). Must not be null or blank.
     * @return This styler instance for chaining.
     */
    public S backgroundPosition(String position) {
        Args.requireNotNull(position, "position");
        Args.requireNotEmpty(position, "position"); // Ensure the string is not empty
        addStyle("-fx-background-position", position);
        return self();
    }

    /**
     * Sets the {@code -fx-background-repeat} CSS property.
     * Defines how the background image is repeated.
     *
     * @param repeat A {@link BackgroundRepeatValue} enum constant (e.g., {@code BackgroundRepeatValue.NO_REPEAT}). Must not be null.
     * @return This styler instance for chaining.
     */
    public S backgroundRepeat(BackgroundRepeatValue repeat) {
        Args.requireNotNull(repeat, "repeat");
        addStyle("-fx-background-repeat", repeat.getCssValue());
        return self();
    }

    /**
     * Sets the {@code -fx-background-size} CSS property using predefined enum values.
     * Defines the size of the background image.
     *
     * @param size A {@link BackgroundSizeValue} enum constant (e.g., {@code BackgroundSizeValue.COVER}). Must not be null.
     * @return This styler instance for chaining.
     */
    public S backgroundSize(BackgroundSizeValue size) {
        Args.requireNotNull(size, "size");
        addStyle("-fx-background-size", size.getCssValue());
        return self();
    }

    /**
     * Sets the {@code -fx-background-size} CSS property using a custom string value.
     * Defines the size of the background image. This allows for values not covered
     * by the {@link BackgroundSizeValue} enum, such as specific length or
     * percentage pairs (e.g., "100px auto", "50% 25%").
     *
     * @param size The CSS string for the background size (e.g., "auto", "cover", "100px 50%"). Must not be null or blank.
     * @return This styler instance for chaining.
     */
    public S backgroundSize(String size) {
        Args.requireNotNull(size, "size");
        Args.requireNotEmpty(size, "size"); // Ensure the string is not empty
        addStyle("-fx-background-size", size);
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

}