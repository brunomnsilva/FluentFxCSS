package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import com.brunomnsilva.fluentfxcss.util.Args;
import com.brunomnsilva.fluentfxcss.util.CssHelper;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

/**
 * An abstract styler for defining CSS properties specific to JavaFX {@link Shape} nodes
 * (e.g., {@link javafx.scene.shape.Rectangle}, {@link javafx.scene.shape.Circle}, {@link javafx.scene.shape.Path}).
 * <p>
 * This class extends {@link NodeStyler} and adds fluent methods for common
 * shape-related styles such as fill, stroke appearance (color, width, type, dashing),
 * and smoothing. Subclasses, like concrete stylers for specific Shape types or a general
 * {@code FluentShapeStyler}, will extend this class.
 * </p>
 * <p>
 * Note: {@link Shape} nodes in JavaFX use {@code -fx-fill} for their interior color and
 * {@code -fx-stroke} for their outline, distinct from {@link javafx.scene.layout.Region}
 * properties like {@code -fx-background-color} and {@code -fx-border-color}.
 * </p>
 *
 * @param <S> The concrete type of the styler extending this class (CRTP).
 * @param <D> The specific type of {@link StyleDefinition} this styler builds.
 * @see NodeStyler
 * @see StyleDefinition
 * @see javafx.scene.shape.Shape
 *
 * @author brunomnsilva
 */
public abstract class ShapeStyler<S extends ShapeStyler<S, D>, D extends StyleDefinition>
        extends NodeStyler<S, D> {

    /**
     * Protected constructor for subclasses.
     * Initializes the styler which is a descendant of {@link NodeStyler}.
     */
    public ShapeStyler() {
        super();
    }

    /**
     * Sets the {@code -fx-fill} CSS property, defining the paint used to fill the interior of the shape or text.
     * This method accepts any {@link Paint} object. The underlying {@link CssHelper#toCssPaint(Paint)}
     * method will attempt to convert it into a valid CSS string representation.
     * Currently, it supports:
     * <ul>
     *     <li>{@link Color}: Converted to a CSS color string (e.g., "#RRGGBBAA", "rgba(...)").</li>
     *     <li>{@link LinearGradient}: Converted to a CSS {@code linear-gradient(...)} string.</li>
     *     <li>{@link RadialGradient}: Converted to a CSS {@code radial-gradient(...)} string.</li>
     * </ul>
     * If a {@code null} paint is provided, the fill is typically set to "transparent" or the property
     * might be effectively unset depending on the CSS engine's interpretation of an empty or transparent fill.
     * For other {@code Paint} subtypes not explicitly handled by {@code CssHelper.toCssPaint} (e.g., {@code ImagePattern}),
     * the conversion will fall back to {@code paint.toString()}, which may not produce a valid CSS syntax.
     * <p>
     * When used in {@link TextStyler}, this method effectively sets the text color.
     * </p>
     *
     * @param paint The {@link Paint} to use for the fill (e.g., {@code Color}, {@code LinearGradient},
     *              {@code RadialGradient}). Can be null.
     * @return This styler instance for chaining.
     * @see CssHelper#toCssPaint(Paint)
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typepaint">JavaFX CSS Paint</a>
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#shape">JavaFX CSS Shape (-fx-fill)</a>
     */
    public S fill(Paint paint) {
        addStyle("-fx-fill", CssHelper.toCssPaint(paint));
        return self();
    }

    /**
     * Sets the {@code -fx-smooth} CSS property.
     * If true, requests that the shape be rendered with antialiasing hints.
     *
     * @param value True to enable smoothing, false otherwise.
     * @return This styler instance for chaining.
     */
    public S smooth(boolean value) {
        addStyle("-fx-smooth", String.valueOf(value));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke} CSS property, defining the paint used to draw the outline of the shape.
     *
     * @param paint The {@link Paint} (e.g., {@link javafx.scene.paint.Color}, gradient) for the stroke.
     * @return This styler instance for chaining.
     */
    public S stroke(Paint paint) {
        addStyle("-fx-stroke", CssHelper.toCssPaint(paint));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-type} CSS property.
     * Defines whether the stroke is drawn centered, inset, or outset relative to the shape's geometry.
     *
     * @param type The {@link StrokeType} (CENTERED, INSIDE, OUTSIDE).
     * @return This styler instance for chaining.
     */
    public S strokeType(StrokeType type) {
        addStyle("-fx-stroke-type", CssHelper.toCssStrokeType(type));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-dash-array} CSS property.
     * Defines the pattern of dashes and gaps used to stroke the shape.
     * Each number is a length, typically in pixels.
     *
     * @param dash Varargs array of integers representing the dash array (e.g., 10, 5 for a dash of 10 and a gap of 5).
     * @return This styler instance for chaining.
     */
    public S strokeDashArray(Integer... dash) {
        Args.requireNotNull(dash, "dash");

        addStyle("-fx-stroke-dash-array", CssHelper.toIntegerArrayString(dash));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-dash-offset} CSS property.
     * Specifies the distance into the dash pattern at which to start the dashing.
     *
     * @param offset The offset value in pixels.
     * @return This styler instance for chaining.
     */
    public S strokeDashOffset(double offset) {
        addStyle("-fx-stroke-dash-offset", CssHelper.toDoubleString(UnitValue.NONE, offset));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-line-cap} CSS property.
     * Defines the shape to be used at the ends of open subpaths when they are stroked.
     *
     * @param type The {@link StrokeLineCap} (BUTT, ROUND, SQUARE). If {@code null}, defaults to {@code SQUARE}.
     * @return This styler instance for chaining.
     */
    public S strokeLineCap(StrokeLineCap type) {
        addStyle("-fx-stroke-line-cap", CssHelper.toCssStrokeLineCap(type));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-line-join} CSS property.
     * Defines the shape to be used at the corners of paths when they are stroked.
     *
     * @param type The {@link StrokeLineJoin} (MITER, BEVEL, ROUND). If {@code null}, defaults to {@code MITER}.
     * @return This styler instance for chaining.
     */
    public S strokeLineJoin(StrokeLineJoin type) {
        addStyle("-fx-stroke-line-join", CssHelper.toCssStrokeLineJoin(type));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-miter-limit} CSS property.
     * When the {@code strokeLineJoin} is MITER, this limits the ratio of miter length to stroke width.
     *
     * @param limit The miter limit value. Must be &ge; 1.0.
     * @return This styler instance for chaining.
     */
    public S strokeMiterLimit(double limit) {
        Args.requireInInterval(limit, 1, Double.MAX_VALUE, "limit");
        addStyle("-fx-stroke-miter-limit", CssHelper.toDoubleString(UnitValue.NONE, limit));
        return self();
    }

    /**
     * Sets the {@code -fx-stroke-width} CSS property, assuming pixel units.
     * Defines the width of the stroke.
     *
     * @param value The stroke width in pixels.
     * @return This styler instance for chaining.
     */
    public S strokeWidth(double value) {
        return strokeWidth(value, UnitValue.PX);
    }

    /**
     * Sets the {@code -fx-stroke-width} CSS property with a specified unit.
     * Defines the width of the stroke.
     *
     * @param value The stroke width.
     * @param unit  The unit for the stroke width.
     * @return This styler instance for chaining.
     */
    public S strokeWidth(double value, UnitValue unit) {
        addStyle("-fx-stroke-width", CssHelper.toDoubleString(unit, value));
        return self();
    }

}
