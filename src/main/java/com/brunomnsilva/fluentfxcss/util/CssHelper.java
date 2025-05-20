package com.brunomnsilva.fluentfxcss.util;

import com.brunomnsilva.fluentfxcss.enums.BorderStyleValue;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class CssHelper {

    private CssHelper() {
        // Utility class
    }


    public static String toCssColor(Color color) {
        if (color == null) return "transparent";

        // Format to #RRGGBBAA - JavaFX uses this format for setStyle
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        int a = (int) (color.getOpacity() * 255);
        return String.format("#%02x%02x%02x%02x", r, g, b, a).toUpperCase(Locale.ROOT);
    }

    public static String toCssPaint(Paint paint) {
        if (paint instanceof Color) {
            return toCssColor((Color) paint);
        }
        // For V1, we'll keep it simple. Gradients would need more complex parsing.
        // Returning paint.toString() might work for some simple named colors if not Color instances.
        // A robust solution would parse LinearGradient, RadialGradient, etc.
        if (paint != null) {
            return paint.toString(); // This is a simplification for V1
        }
        return "transparent";
    }

    public static String toCssDropShadow(Color color, double radius, double offsetX, double offsetY) {
        // JavaFX CSS for DropShadow: dropshadow( blurType, color, radius, spread, offsetX, offsetY )
        // We'll use Gaussian blur and 0 spread for simplicity in V1
        return String.format(Locale.US, "dropshadow(gaussian, %s, %.2f, 0, %.2f, %.2f)",
                toCssColor(color), radius, offsetX, offsetY);
    }

    public static String toCssCursor(Cursor cursor) {
        if (cursor == null) return "default";
        // JavaFX Cursor names often map directly, but some might need conversion.
        // E.g., Cursor.HAND -> "hand"
        return cursor.toString().toLowerCase(Locale.ROOT); // This is a good first approximation
    }

    public static String toCssBorderStyle(BorderStyleValue style) {
        if (style == null) return "none";
        return style.getCssValue();
    }

    public static String toCssFontWeight(FontWeight weight) {
        if (weight == null) return "normal";
        // FontWeight.BOLD.name() -> "BOLD" -> "bold"
        return weight.name().toLowerCase(Locale.ROOT);
    }

    public static String toCssFontPosture(FontPosture posture) {
        if (posture == null) return "normal"; // Or "regular" maps to "normal" in CSS
        return posture.name().toLowerCase(Locale.ROOT);
    }

    public static String toCssTextAlignment(TextAlignment alignment) {
        if (alignment == null) return "left";
        return alignment.name().toLowerCase(Locale.ROOT);
    }

    public static String toCssStrokeType(StrokeType strokeType) {
        if (strokeType == null) return "centered"; // Default
        return strokeType.name().toLowerCase(Locale.ROOT);
    }

    public static String toDoubleArrayString(double... values) {
        return Arrays.stream(values)
                .mapToObj(d -> String.format(Locale.US, "%.2fpx", d)) // Assuming px, V1
                .collect(Collectors.joining(" "));
    }
    public static String toDoubleString(double value) {
        return String.format(Locale.US, "%.2fpx", value); // Assuming px, V1
    }
}