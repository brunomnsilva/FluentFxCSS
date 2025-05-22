package com.brunomnsilva.fluentfxcss.util;

import com.brunomnsilva.fluentfxcss.enums.BorderStyleValue;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import javafx.scene.Cursor;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Utility class for converting JavaFX styling objects into CSS-compatible string values.
 * <p>
 * This class provides static helper methods for formatting colors, paints, fonts, borders,
 * and other visual properties into valid CSS strings that can be used in {@code Node.setStyle(String)}.
 * </p>
 *
 * @author brunomnsilva
 */
public class CssHelper {

    private CssHelper() {
        // Utility class; no instances allowed.
    }

    /**
     * Converts a {@link Color} to a CSS-compatible color string.
     *
     * @param color the JavaFX {@code Color} instance
     * @return CSS color string (e.g., {@code #RRGGBBAA}) or {@code "transparent"} if transparent
     */
    public static String toCssColor(Color color) {
        if (color == null) return "null";
        if (color.equals(Color.TRANSPARENT)) return "transparent";

        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        int a = (int) (color.getOpacity() * 255);
        return String.format("#%02x%02x%02x%02x", r, g, b, a).toUpperCase(Locale.ROOT);
    }

    /**
     * Converts a {@link Paint} to a CSS-compatible string.
     * Only {@link Color} is handled explicitly.
     *
     * @param paint the JavaFX {@code Paint} instance
     * @return CSS-compatible string or {@code "transparent"} if null
     */
    public static String toCssPaint(Paint paint) {
        if(paint == null) return "transparent";

        if (paint instanceof Color) {
            return toCssColor((Color) paint);
        }

        if(paint instanceof LinearGradient) {
            return toCssLinearGradient((LinearGradient) paint);
        }

        if(paint instanceof RadialGradient) {
            return toCssRadialGradient((RadialGradient) paint);
        }

        return paint.toString(); // Fallback, may produce invalid results
    }

    /**
     * Converts a {@link javafx.scene.paint.LinearGradient} instance to a valid CSS linear-gradient string.
     * <p>
     * The resulting CSS string includes:
     * <ul>
     *   <li>Start and end positions in percentage format (e.g., "from 0% 0% to 100% 100%").</li>
     *   <li>Optional cycle method keyword ("repeat" or "reflect") if applicable.</li>
     *   <li>Color stops with offset percentages and RGBA hex values.</li>
     * </ul>
     * <p>
     * Example output:
     * <pre>
     * linear-gradient(from 0.00% 0.00% to 100.00% 100.00%, #FF0000FF 0.00%, #0000FFFF 100.00%)
     * </pre>
     *
     * @param gradient the {@code LinearGradient} instance to convert; if {@code null}, returns {@code "transparent"}
     * @return the CSS string representing the linear gradient
     */
    public static String toCssLinearGradient(LinearGradient gradient) {
        if (gradient == null) return "transparent";

        String direction = String.format(Locale.US, "from %.2f%% %.2f%% to %.2f%% %.2f%%",
                gradient.getStartX() * 100,
                gradient.getStartY() * 100,
                gradient.getEndX() * 100,
                gradient.getEndY() * 100
        );

        String cycle = "";
        if (gradient.getCycleMethod() == CycleMethod.REPEAT) {
            cycle = " repeat";
        } else if (gradient.getCycleMethod() == CycleMethod.REFLECT) {
            cycle = " reflect";
        }

        String stops = gradient.getStops().stream()
                .map(stop -> String.format(Locale.US, " %s %.2f%%",
                        CssHelper.toCssColor(stop.getColor()),
                        stop.getOffset() * 100))
                .collect(Collectors.joining(","));

        return String.format("linear-gradient(%s%s,%s)", direction, cycle, stops);
    }

    /**
     * Converts a {@link javafx.scene.paint.RadialGradient} instance to a valid CSS radial-gradient string.
     * <p>
     * The CSS output includes:
     * <ul>
     *   <li>Focus angle, focus distance, center position, and radius (all in percentage when applicable).</li>
     *   <li>Cycle method if set to {@code REFLECT} or {@code REPEAT}.</li>
     *   <li>Color stops with offset percentages and RGBA hex values.</li>
     * </ul>
     * <p>
     * Example output:
     * <pre>
     * radial-gradient(focus-angle 0.0deg, focus-distance 0.5, center 50.0% 50.0%, radius 50.0%, repeat,
     *     #FF0000FF 0.0%, #0000FFFF 100.0%)
     * </pre>
     *
     * @param gradient the {@code RadialGradient} instance to convert; if {@code null}, returns {@code "transparent"}
     * @return the CSS string representing the radial gradient
     */
    public static String toCssRadialGradient(RadialGradient gradient) {
        if (gradient == null) return "transparent";

        StringBuilder sb = new StringBuilder("radial-gradient(");

        // Focus angle (in degrees)
        sb.append(String.format(Locale.US, "focus-angle %.1fdeg, ", gradient.getFocusAngle()));

        // Focus distance
        sb.append(String.format(Locale.US, "focus-distance %.2f%%, ", gradient.getFocusDistance()));

        // Center
        sb.append(String.format(Locale.US, "center %.1f%% %.1f%%, ",
                gradient.getCenterX() * 100, gradient.getCenterY() * 100));

        // Radius
        sb.append(String.format(Locale.US, "radius %.1f%%", gradient.getRadius() * 100));

        // Cycle method (only if not NO_CYCLE)
        CycleMethod cycleMethod = gradient.getCycleMethod();
        if (cycleMethod != CycleMethod.NO_CYCLE) {
            sb.append(", ").append(cycleMethod.name().toLowerCase(Locale.ROOT));
        }

        // Stops
        sb.append(", ");
        sb.append(
                gradient.getStops().stream()
                        .map(stop -> String.format(Locale.US, "%s %.1f%%",
                                toCssColor(stop.getColor()), stop.getOffset() * 100))
                        .collect(Collectors.joining(", "))
        );

        sb.append(")");
        return sb.toString();
    }

    /**
     * Converts a JavaFX {@link SVGPath} into a CSS-compatible string representation
     * suitable for use in JavaFX stylesheets.
     * <p>
     * In JavaFX CSS, an {@code SVGPath} can be applied using the {@code -fx-shape} property.
     * This method extracts the SVG path content and wraps it in double quotes, which is
     * required for the CSS syntax.
     * <p>
     * Example output: {@code "M10 10 H 90 V 90 H 10 Z"}
     *
     * @param svgPath the {@code SVGPath} instance to convert
     * @return a valid CSS string representation of the path, or {@code "null"} if {@code svgPath} is {@code null}
     * @see <a href="https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/shape/SVGPath.html">SVGPath Javadoc</a>
     */
    public static String toCssSvgPath(SVGPath svgPath) {
        if (svgPath == null || svgPath.getContent() == null || svgPath.getContent().isBlank()) {
            return "null"; // fallback when no path is defined
        }

        return "\"" + svgPath.getContent().trim() + "\"";
    }

    /**
     * Converts a {@link DropShadow} object to its corresponding JavaFX CSS string representation.
     * <p>
     * The returned string is in the format expected by JavaFX for the {@code -fx-effect} property:
     * {@code dropshadow(blurType, color, radius, spread, offsetX, offsetY)}.
     *
     * @param dropShadow the {@code DropShadow} effect to convert
     * @return a CSS string representing the drop shadow effect
     */
    public static String toCssDropShadow(DropShadow dropShadow) {
        if(dropShadow == null) return "null";

        return String.format(Locale.US, "dropshadow(%s, %s, %.2f, %.2f, %.2f, %.2f)",
                toCssBlurType(dropShadow.getBlurType()),
                toCssColor(dropShadow.getColor()),
                dropShadow.getRadius(),
                dropShadow.getSpread(),
                dropShadow.getOffsetX(),
                dropShadow.getOffsetY());
    }

    /**
     * Converts a {@link InnerShadow} object to its corresponding JavaFX CSS string representation.
     * <p>
     * The returned string is in the format expected by JavaFX for the {@code -fx-effect} property:
     * {@code innershadow(blurType, color, radius, choke, offsetX, offsetY)}.
     *
     * @param innerShadow the {@code InnerShadow} effect to convert
     * @return a CSS string representing the drop shadow effect
     */
    public static String toCssInnerShadow(InnerShadow innerShadow) {
        if(innerShadow == null) return "null";

        return String.format(Locale.US, "innershadow(%s, %s, %.2f, %.2f, %.2f, %.2f)",
                toCssBlurType(innerShadow.getBlurType()),
                toCssColor(innerShadow.getColor()),
                innerShadow.getRadius(),
                innerShadow.getChoke(),
                innerShadow.getOffsetX(),
                innerShadow.getOffsetY());
    }

    /**
     * Converts a {@link BlurType} to a CSS blur type name.
     *
     * @param type the JavaFX {@code BlurType}
     * @return CSS-compatible blur type name
     */
    public static String toCssBlurType(BlurType type) {
        if(type == null) return "three-pass-box"; // Default

        String inducedType = type.toString().toLowerCase(Locale.ROOT);
        return inducedType.replace('_', '-');
    }

    /**
     * Converts a {@link Cursor} to a CSS cursor name.
     *
     * @param cursor the JavaFX {@code Cursor}
     * @return CSS-compatible cursor name
     */
    public static String toCssCursor(Cursor cursor) {
        if (cursor == null) return "null";

        String name = cursor.toString().toLowerCase(Locale.ROOT);
        switch (name) {
            case "disappear":
                return "null";
            case "open_hand":
            case "closed_hand":
                return "hand";
            default:
                return name;
        }
    }

    /**
     * Converts a custom {@code BorderStyleValue} to its CSS value.
     *
     * @param style the style enum or class
     * @return the CSS string, or {@code "null"} if null
     */
    public static String toCssBorderStyle(BorderStyleValue style) {
        if (style == null) return "null";
        return style.getCssValue();
    }

    /**
     * Converts a JavaFX {@link FontWeight} enum to its corresponding CSS string representation.
     * Handles mapping enum names to standard CSS font weight keywords or numeric values.
     * If the provided weight is null, defaults to "inherit".
     *
     * @param weight The FontWeight enum instance.
     * @return The CSS string for the font weight (e.g., "normal", "bold", "700").
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/font-weight">MDN font-weight</a>
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typefont">JavaFX CSS Font</a>
     */
    public static String toCssFontWeight(FontWeight weight) {
        if (weight == null) {
            return "inherit";
        }

        switch (weight) {
            case THIN:
                return "100";
            case EXTRA_LIGHT:
                return "200";
            case LIGHT:
                return "300";
            case NORMAL: // FontWeight.NORMAL.getWeight() is 400
                return "normal"; // or "400" - "normal" is often preferred
            case MEDIUM: // FontWeight.MEDIUM.getWeight() is 500
                return "500"; // "medium" is not a standard CSS keyword, use numeric
            case SEMI_BOLD: // FontWeight.SEMI_BOLD.getWeight() is 600
                return "600"; // "semi-bold" is not a standard CSS keyword
            case BOLD: // FontWeight.BOLD.getWeight() is 700
                return "bold"; // or "700" - "bold" is often preferred
            case EXTRA_BOLD: // FontWeight.EXTRA_BOLD.getWeight() is 800
                return "800";
            case BLACK: // FontWeight.BLACK.getWeight() is 900
                return "900"; // "black" is not a standard CSS keyword, use numeric (though some browsers map it)
            default:
                // The FontWeight.getWeight() method returns an int (100-900).
                return String.valueOf(weight.getWeight()); // Fallback to numeric weight
        }
    }

    /**
     * Converts a {@link FontPosture} to its CSS string.
     *
     * @param posture font posture
     * @return CSS-compatible posture value
     */
    public static String toCssFontPosture(FontPosture posture) {
        if (posture == null) return "inherit";
        return posture.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a {@link TextAlignment} to a CSS-aligned string.
     *
     * @param alignment text alignment
     * @return CSS string for alignment
     */
    public static String toCssTextAlignment(TextAlignment alignment) {
        if (alignment == null) return "left";
        return alignment.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a {@link FontSmoothingType} to its corresponding CSS string value.
     * Returns {@code "gray"} if the argument is {@code null}.
     *
     * @param type the font smoothing type, may be {@code null}
     * @return the CSS string representation
     */
    public static String toCssFontSmoothingType(FontSmoothingType type) {
        if(type == null) return "gray";
        return type.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a {@link StrokeType} to its corresponding CSS string value.
     * Returns {@code "centered"} if the argument is {@code null}.
     *
     * @param strokeType the stroke type, may be {@code null}
     * @return the CSS string representation
     */
    public static String toCssStrokeType(StrokeType strokeType) {
        if (strokeType == null) return "null";
        return strokeType.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a {@link StrokeLineCap} to its corresponding CSS string value.
     * Returns {@code "square"} if the argument is {@code null}.
     *
     * @param type the stroke line cap type, may be {@code null}
     * @return the CSS string representation
     */
    public static String toCssStrokeLineCap(StrokeLineCap type) {
        if (type == null) return "null";
        return type.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts a {@link StrokeLineJoin} to its corresponding CSS string value.
     * Returns {@code "miter"} if the argument is {@code null}.
     *
     * @param type the stroke line join type, may be {@code null}
     * @return the CSS string representation
     */
    public static String toCssStrokeLineJoin(StrokeLineJoin type) {
        if (type == null) return "null";
        return type.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Converts an array of {@code double} values into a CSS string, appending the given unit to each value.
     * Values are formatted with 2 decimal places and separated by spaces.
     * If {@code unit} is {@code null}, {@code UnitValue.PX} is used as default.
     *
     * @param unit   the unit to append, or {@code null} for pixels
     * @param values the double values to convert
     * @return the formatted CSS string, or {@code "null"} if {@code values} is {@code null}
     */
    public static String toDoubleArrayString(UnitValue unit, double... values) {
        final UnitValue effectiveUnit = unit != null ? unit : UnitValue.PX;
        if(values == null) return "null";

        return Arrays.stream(values)
                .mapToObj(d -> String.format(Locale.US, "%.2f%s", d, effectiveUnit.getCssValue()))
                .collect(Collectors.joining(" "));
    }

    /**
     * Converts a {@code double} value into a CSS string with the given unit.
     * Value is formatted with 2 decimal places.
     * If {@code unit} is {@code null}, {@code UnitValue.PX} is used as default.
     *
     * @param unit  the unit to append, or {@code null} for pixels
     * @param value the value to convert
     * @return the formatted CSS string
     */
    public static String toDoubleString(UnitValue unit, double value) {
        final UnitValue effectiveUnit = unit != null ? unit : UnitValue.PX;
        return String.format(Locale.US, "%.2f%s", value, effectiveUnit.getCssValue());
    }

    /**
     * Converts an array of {@code Integer} values into a space-separated string.
     * If {@code values} is {@code null}, returns {@code "null"}.
     *
     * @param values the integer values to convert
     * @return the formatted string or {@code "null"} if {@code values} is {@code null}
     */
    public static String toIntegerArrayString(Integer... values) {
        if(values == null) return "null";
        return Arrays.stream(values)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}