package com.brunomnsilva.fluentfxcss.util;

import com.brunomnsilva.fluentfxcss.enums.BorderStyleValue;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import javafx.scene.Cursor;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        if(paint == null) return "null";

        if (paint instanceof Color) {
            return toCssColor((Color) paint);
        }

        return paint.toString(); // Fallback
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