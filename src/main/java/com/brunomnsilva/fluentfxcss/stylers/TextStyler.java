package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import com.brunomnsilva.fluentfxcss.util.Args;
import com.brunomnsilva.fluentfxcss.util.CssHelper;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;

/**
 * An abstract styler for defining CSS properties specific to JavaFX {@link Text} nodes.
 * <p>
 * This class extends {@link ShapeStyler} because {@link Text} is a subclass of {@link Shape}
 * in JavaFX, thus inheriting shape properties like {@code -fx-fill} (for text color)
 * and {@code -fx-stroke} (for text outline). It adds fluent methods for common
 * text-specific styles such as font properties (family, size, weight, style),
 * decorations (underline, strikethrough), text alignment, and font smoothing.
 * </p>
 * <p>
 * Subclasses, like a concrete {@code FluentTextNodeStyler}, will extend this class.
 * For font sizes, points ({@link UnitValue#PT}) are typically assumed if no unit is specified,
 * aligning with common typographic conventions.
 * </p>
 *
 * @param <S> The concrete type of the styler extending this class (CRTP).
 * @param <D> The specific type of {@link StyleDefinition} this styler builds.
 * @see ShapeStyler
 * @see NodeStyler
 * @see StyleDefinition
 * @see javafx.scene.text.Text
 * @see javafx.scene.shape.Shape
 *
 * @author brunomnsilva
 */
public abstract class TextStyler<S extends TextStyler<S, D>, D extends StyleDefinition>
        extends ShapeStyler<S, D> { // Text extends Shape, so TextStyler extends ShapeStyler

    /**
     * Protected constructor for subclasses.
     * Initializes the styler which is a descendant of {@link ShapeStyler}.
     */
    public TextStyler() {
        super();
    }

    // -- Text Fill (alias)
    /**
     * Sets the fill color or paint for the text, effectively an alias for {@link #fill(Paint)}.
     * <p>
     * The JavaFX CSS property for text color is {@code -fx-fill}. This method provides
     * a semantically clearer way to set the text's appearance when compared to using
     * the generic {@code fill()} method inherited from {@link ShapeStyler}.
     * </p>
     * <p>
     * This method accepts any {@link Paint} object. The underlying {@link com.brunomnsilva.fluentfxcss.util.CssHelper#toCssPaint(Paint)}
     * method will attempt to convert it into a valid CSS string representation.
     * Supported {@code Paint} types include:
     * <ul>
     *     <li>{@link javafx.scene.paint.Color}: Converted to a CSS color string.</li>
     *     <li>{@link javafx.scene.paint.LinearGradient}: Converted to a CSS {@code linear-gradient(...)} string.</li>
     *     <li>{@link javafx.scene.paint.RadialGradient}: Converted to a CSS {@code radial-gradient(...)} string.</li>
     * </ul>
     * If a {@code null} paint is provided, the fill is set to "transparent".
     * </p>
     *
     * @param paint The {@link Paint} to use for the text's fill (e.g., {@code Color},
     *              {@code LinearGradient}, {@code RadialGradient}). Can be null.
     * @return This styler instance for chaining.
     * @see #fill(Paint)
     * @see com.brunomnsilva.fluentfxcss.util.CssHelper#toCssPaint(Paint)
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#text">JavaFX CSS Text (-fx-fill)</a>
     */
    public S textFill(Paint paint) {
        // Delegates to the fill method inherited from ShapeStyler,
        // which sets the -fx-fill CSS property.
        return fill(paint);
    }

    // --- Font Properties ---

    /**
     * Sets the {@code -fx-font-family} CSS property.
     * Defines the family of the font. Font families containing spaces should be quoted.
     *
     * @param family The font family name (e.g., "Arial", "Times New Roman").
     * @return This styler instance for chaining.
     */
    public S fontFamily(String family) {
        // Quotes are important for families with spaces.
        addStyle("-fx-font-family", (family != null && family.contains(" ")) ? "\"" + family + "\"" : family);
        return self();
    }

    /**
     * Sets the {@code -fx-font-size} CSS property, assuming point (PT) units.
     * Defines the size of the font.
     *
     * @param size The font size in points (PT).
     * @return This styler instance for chaining.
     */
    public S fontSize(double size) {
        return fontSize(size, UnitValue.PT);
    }

    /**
     * Sets the {@code -fx-font-size} CSS property, assuming point (PT) units.
     * Defines the size of the font.
     *
     * @param size The font size.
     * @param unit The unit for the font size.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code unit} is {@code null}.
     */
    public S fontSize(double size, UnitValue unit) {
        Args.requireNotNull(unit, "unit");

        addStyle("-fx-font-size", CssHelper.toDoubleString(unit, size));
        return self();
    }

    /**
     * Sets the {@code -fx-font-weight} CSS property.
     * Defines the boldness of the font.
     *
     * @param weight The {@link FontWeight} (e.g., NORMAL, BOLD).
     * @return This styler instance for chaining.
     */
    public S fontWeight(FontWeight weight) {
        addStyle("-fx-font-weight", CssHelper.toCssFontWeight(weight));
        return self();
    }

    /**
     * Sets the {@code -fx-font-style} CSS property.
     * Defines the posture of the font (e.g., regular or italic).
     *
     * @param posture The {@link FontPosture} (e.g., REGULAR, ITALIC).
     * @return This styler instance for chaining.
     */
    public S fontStyle(FontPosture posture) {
        addStyle("-fx-font-style", CssHelper.toCssFontPosture(posture));
        return self();
    }

    /**
     * Sets multiple font properties (family, weight, posture, and size) at once
     * using a JavaFX {@link Font} object.
     * <p>
     * This method acts as a convenient way to apply a complete font definition.
     * Calling this method will effectively override any individual font properties
     * (e.g., {@link #fontFamily(String)}, {@link #fontSize(double)}, {@link #fontWeight(FontWeight)},
     * {@link #fontStyle(FontPosture)}) that were set previously on this styler instance,
     * as it calls these individual setter methods with values extracted from the provided {@code Font} object.
     * </p>
     * <p>
     * The font size extracted from the {@code Font} object (which is in points) will be
     * set using {@link UnitValue#PT}.
     * </p>
     *
     * @param font The {@link Font} object to extract styling information from. Must not be null.
     * @return This styler instance for chaining.
     * @throws IllegalArgumentException if the font is null.
     */
    public S font(Font font) {
        Args.requireNotNull(font, "font");

        // Extract properties from the Font object and call existing methods
        fontFamily(font.getFamily());
        fontWeight(FontWeight.findByName(font.getStyle()));
        fontStyle(FontPosture.findByName(font.getStyle()));
        fontSize(font.getSize()); // Sets -fx-font-size in PT (as fontSize method defaults to PT)

        return self();
    }

    // --- Text-specific Properties ---

    /**
     * Sets the {@code -fx-font-smoothing-type} CSS property.
     * Defines the type of font smoothing (antialiasing) applied to the text.
     *
     * @param type The {@link FontSmoothingType} (e.g., GRAY, LCD).
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code type} is {@code null}
     */
    public S smoothing(FontSmoothingType type) {
        Args.requireNotNull(type, "type");
        addStyle("-fx-font-smoothing-type", CssHelper.toCssFontSmoothingType(type));
        return self();
    }

    /**
     * Sets the {@code -fx-underline} CSS property.
     * Specifies whether the text should be underlined.
     *
     * @param value True to underline the text, false otherwise.
     * @return This styler instance for chaining.
     */
    public S underline(boolean value) {
        addStyle("-fx-underline", String.valueOf(value));
        return self();
    }

    /**
     * Sets the {@code -fx-strikethrough} CSS property.
     * Specifies whether the text should have a line through it.
     *
     * @param value True to apply strikethrough, false otherwise.
     * @return This styler instance for chaining.
     */
    public S strikethrough(boolean value) {
        addStyle("-fx-strikethrough", String.valueOf(value));
        return self();
    }

    /**
     * Sets the {@code -fx-text-alignment} CSS property.
     * Defines the horizontal alignment of the text within its bounds.
     *
     * @param alignment The {@link TextAlignment} (e.g., LEFT, CENTER, RIGHT, JUSTIFY).
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if {@code alignment} is {@code null}
     */
    public S alignment(TextAlignment alignment) {
        Args.requireNotNull(alignment, "alignment");
        addStyle("-fx-text-alignment", CssHelper.toCssTextAlignment(alignment));
        return self();
    }

    // TODO: Consider adding textFill() as an alias for fill() inherited from ShapeStyler for clarity:
    /*
    public S textFill(Paint paint) {
        return fill(paint); // Delegates to ShapeStyler's fill method
    }
    */
}
