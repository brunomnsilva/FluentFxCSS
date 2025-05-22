package com.brunomnsilva.fluentfxcss.stylers;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.util.Args;
import com.brunomnsilva.fluentfxcss.util.CssHelper;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * An abstract base class for all styler builders in the FluentFxCss library.
 * <p>
 * This class provides the fundamental mechanism for collecting CSS property-value
 * pairs in a fluent manner. It defines methods for styling properties that are
 * applicable to the base {@link Node} class and inherited by all JavaFX nodes.
 * </p>
 * <p>
 * Subclasses extend this base class to add styling methods for properties specific
 * to their respective node categories (e.g., {@link RegionStyler}, {@link ShapeStyler},
 * {@link TextStyler}).
 * </p>
 * <p>
 * The styler methods in this class (and its subclasses) return the concrete
 * builder type {@code S} (using the Curiously Recurring Template Pattern - CRTP)
 * to enable fluent chaining of method calls.
 * </p>
 * The terminal method {@code build()} is used to finalize the styling process and
 * create an immutable {@link StyleDefinition} object encapsulating the collected styles.
 *
 * @param <S> The concrete type of the styler extending this base class.
 *            This is used for the fluent return types of the methods.
 * @param <D> The specific type of {@link StyleDefinition} that this styler (or its
 *            concrete descendant) will build.
 *
 * @see StyleDefinition
 * @see RegionStyler
 * @see ShapeStyler
 * @see TextStyler
 * @see com.brunomnsilva.fluentfxcss.FluentFxCss
 *
 * @author brunomnsilva
 */
public abstract class NodeStyler<S extends NodeStyler<S, D>, D extends StyleDefinition> {

    /** property:value mappings */
    protected final Map<String, String> styles = new LinkedHashMap<>();

    protected NodeStyler() {
    }

    /**
     * Returns the concrete styler instance ({@code this}) cast to the
     * self-referential type {@code S}.
     * This method is used internally by the fluent styling methods to
     * ensure method chaining returns the correct concrete type.
     *
     * @return The concrete styler instance.
     */
    @SuppressWarnings("unchecked")
    protected S self() {
        return (S) this;
    }

    /**
     * Adds a CSS property-value pair to the collection of styles being built.
     * This method is typically used by the specific styling methods (e.g., opacity, cursor).
     *
     * @param property The CSS property name (e.g., "-fx-opacity").
     * @param value    The CSS string representation of the value.
     */
    protected void addStyle(String property, String value) {
        this.styles.put(property.trim(), value);
    }

    /**
     * Sets the {@code -fx-opacity} CSS property.
     * Controls the transparency of the node (0.0 to 1.0).
     *
     * @param value The opacity value (0.0 is fully transparent, 1.0 is fully opaque).
     * @return This styler instance for chaining.
     */
    public S opacity(double value) {
        Args.requireInInterval(value, 0, 1, "value");

        addStyle("-fx-opacity", String.valueOf(value));
        return self();
    }

    /**
     * Sets the {@code -fx-rotate} CSS property.
     * <p>
     * Specifies the angle of rotation for the node around its layout bounds center
     * (or a transform origin if specified by {@code -fx-transform-origin}).
     * The rotation is applied in degrees. Positive values rotate clockwise.
     * </p>
     * <p>
     * Note: The actual pivot point for the rotation can be influenced by other
     * transform properties or the node's default transform origin.
     * For simple rotations around the node's center, this property is often sufficient.
     * For more complex transformations, consider using JavaFX {@link javafx.scene.transform.Rotate}
     * objects added to the node's transforms list.
     * </p>
     *
     * @param degrees The angle of rotation in degrees.
     * @return This styler instance for chaining.
     * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typetransform">JavaFX CSS Transforms</a>
     */
    public S rotate(double degrees) {
        // CSS -fx-rotate expects a number (implicitly degrees)
        addStyle("-fx-rotate", String.format(Locale.US, "%.2f", degrees)); // Format to avoid excessive decimals
        return self();
    }

    /**
     * Sets the {@code -fx-effect} CSS property with a drop shadow.
     * Applies a drop shadow effect to the node.
     * <p>
     *     Other applied defaults:
     *     <ul>
     *         <li>{@code blurType = BlurType.THREE_PASS_BOX}</li>
     *         <li>{@code spread = 0}</li>
     *     </ul>
     * </p>
     * @param color   The color of the drop shadow.
     * @param radius  The radius of the shadow blur.
     * @param offsetX The horizontal offset of the shadow.
     * @param offsetY The vertical offset of the shadow.
     * @return This styler instance for chaining.
     *
     * @see {@link this#dropShadow(BlurType, Color, double, double, double, double)}
     */
    public S dropShadow(Color color, double radius, double offsetX, double offsetY) {
        return dropShadow(BlurType.THREE_PASS_BOX, color, radius, 0, offsetX, offsetY);
    }

    /**
     * Sets the {@code -fx-effect} CSS property to apply a drop shadow.
     * <p>
     * This method constructs a {@link javafx.scene.effect.DropShadow} effect with the specified parameters
     * and then converts it to its CSS string representation.
     * </p>
     * The CSS equivalent is typically {@code dropshadow(blurType, color, radius, spread, offsetX, offsetY)}.
     *
     * @param blurType  The algorithm used to blur the shadow (e.g., {@link BlurType#GAUSSIAN}, {@link BlurType#THREE_PASS_BOX}). Must not be null.
     * @param color     The color of the drop shadow. Must not be null.
     * @param radius    The radius of the shadow blur. Must be in the range {@code [0.0, 127.0]}.
     * @param spread    The spread of the drop shadow, controlling the proportion of the shadow's opaque region.
     *                  Must be in the range {@code [0.0, 1.0]}. A value of 0.0 means a fully blurred shadow,
     *                  while 1.0 means a fully opaque shadow with no blur.
     * @param offsetX   The horizontal offset of the shadow from the source.
     * @param offsetY   The vertical offset of the shadow from the source.
     * @return This styler instance for chaining.
     *
     * @throws IllegalArgumentException if blurType or color is null.
     * @throws IllegalArgumentException if radius or spread is outside its valid range.
     */
    public S dropShadow(BlurType blurType, Color color, double radius, double spread,
                        double offsetX, double offsetY) {
        Args.requireNotNull(blurType, "blurType");
        Args.requireNotNull(color, "color");
        Args.requireInInterval(radius, 0.0, 127.0, "radius");
        Args.requireInInterval(spread, 0.0, 1.0, "spread");

        DropShadow shadow = new DropShadow(blurType, color, radius, spread, offsetX, offsetY);
        addStyle("-fx-effect", CssHelper.toCssDropShadow(shadow));
        return self();
    }

    /**
     * Sets the {@code -fx-effect} CSS property using a pre-configured {@link DropShadow} instance.
     * <p>
     * This method is useful when you have already created and configured a {@code DropShadow}
     * object programmatically and want to apply it as a CSS effect.
     * </p>
     *
     * @param dropShadow The {@link DropShadow} effect instance to apply. Must not be null.
     * @return This styler instance for chaining.
     * @throws NullPointerException if dropShadow is null.
     */
    public S dropShadow(DropShadow dropShadow) {
        Args.requireNotNull(dropShadow, "dropShadow");
        addStyle("-fx-effect", CssHelper.toCssDropShadow(dropShadow));
        return self();
    }

    // --- InnerShadow Methods ---

    /**
     * Sets the {@code -fx-effect} CSS property to apply an inner shadow with default spread (0.0)
     * and {@link BlurType#THREE_PASS_BOX}.
     * <p>
     * This is a convenience method that calls {@link #innerShadow(BlurType, Color, double, double, double, double)}
     * with a spread of 0.0 and {@code BlurType.THREE_PASS_BOX}.
     * </p>
     * The CSS equivalent is typically {@code innershadow(blurType, color, radius, choke, offsetX, offsetY)}.
     *
     * @param color     The color of the inner shadow. Must not be null.
     * @param radius    The radius of the shadow blur. Must be in the range {@code [0.0, 127.0]}.
     * @param offsetX   The horizontal offset of the shadow from the source.
     * @param offsetY   The vertical offset of the shadow from the source.
     * @return This styler instance for chaining.
     * @throws NullPointerException if color is null.
     * @throws IllegalArgumentException if radius is outside its valid range.
     */
    public S innerShadow(Color color, double radius, double offsetX, double offsetY) {
        // Defaulting spread (choke in InnerShadow terms) to 0 for this convenience method.
        // Defaulting BlurType to THREE_PASS_BOX as it's a common default.
        return innerShadow(BlurType.THREE_PASS_BOX, color, radius, 0.0, offsetX, offsetY);
    }

    /**
     * Sets the {@code -fx-effect} CSS property to apply an inner shadow.
     * <p>
     * This method constructs an {@link javafx.scene.effect.InnerShadow} effect with the specified parameters
     * and then converts it to its CSS string representation.
     * </p>
     * The CSS equivalent is typically {@code innershadow(blurType, color, radius, choke, offsetX, offsetY)}.
     *
     * @param blurType  The algorithm used to blur the shadow (e.g., {@link BlurType#GAUSSIAN}, {@link BlurType#THREE_PASS_BOX}). Must not be null.
     * @param color     The color of the inner shadow. Must not be null.
     * @param radius    The radius of the shadow blur. Must be in the range {@code [0.0, 127.0]}.
     * @param choke     The choke of the inner shadow, controlling the proportion of the shadow's opaque region.
     *                  Must be in the range {@code [0.0, 1.0]}. A value of 0.0 means a fully blurred shadow edge,
     *                  while 1.0 means a sharp, unblurred edge.
     * @param offsetX   The horizontal offset of the shadow from the source.
     * @param offsetY   The vertical offset of the shadow from the source.
     * @return This styler instance for chaining.
     * @throws NullPointerException if blurType or color is null.
     * @throws IllegalArgumentException if radius or choke is outside its valid range.
     */
    public S innerShadow(BlurType blurType, Color color, double radius, double choke,
                         double offsetX, double offsetY) {
        Args.requireNotNull(blurType, "blurType"); // Added null check for blurType
        Args.requireNotNull(color, "color");       // Added null check for color
        Args.requireInInterval(radius, 0.0, 127.0, "radius");
        Args.requireInInterval(choke, 0.0, 1.0, "choke");

        InnerShadow shadow = new InnerShadow(blurType, color, radius, choke, offsetX, offsetY);
        addStyle("-fx-effect", CssHelper.toCssInnerShadow(shadow));
        return self();
    }

    /**
     * Sets the {@code -fx-effect} CSS property using a pre-configured {@link InnerShadow} instance.
     * <p>
     * This method is useful when you have already created and configured an {@code InnerShadow}
     * object programmatically and want to apply it as a CSS effect.
     * </p>
     *
     * @param innerShadow The {@link InnerShadow} effect instance to apply. Must not be null.
     * @return This styler instance for chaining.
     * @throws NullPointerException if innerShadow is null.
     */
    public S innerShadow(InnerShadow innerShadow) {
        Args.requireNotNull(innerShadow, "innerShadow");
        addStyle("-fx-effect", CssHelper.toCssInnerShadow(innerShadow));
        return self();
    }

    /**
     * Sets the {@code -fx-cursor} CSS property.
     * Defines the mouse cursor displayed when the pointer is over the node.
     *
     * @param cursor The cursor type.
     * @return This styler instance for chaining.
     */
    public S cursor(Cursor cursor) {
        addStyle("-fx-cursor", CssHelper.toCssCursor(cursor));
        return self();
    }

    /**
     * Sets the {@code visibility} CSS property.
     * Note on behavior:
     * <ul>
     *     <li>{@code true}: The element is visible.</li>
     *     <li>{@code false}: The element is invisible (not drawn), but still affects layout
     *         (space is reserved for it). </li>
     * </ul>
     *
     * @param visible True to make visible, false to hide.
     * @return This styler instance for chaining.
     */
    public S visibility(boolean visible) {
        addStyle("visibility", String.valueOf(visible));
        return self();
    }

    /**
     * Sets a custom CSS property with the given name and value.
     * <p>
     * This method is a fallback for properties not explicitly supported by the
     * fluent API. The property name should be the full CSS property name
     * (e.g., "-fx-some-custom-property"). The value should be a valid CSS value string.
     * </p>
     * <p>
     * Use with caution, as this bypasses type checking and specific value formatting
     * provided by dedicated methods.
     * </p>
     *
     * @param propertyName The full CSS property name (e.g., "-fx-background-repeat").
     * @param cssValue     The raw CSS value string for the property (e.g., "repeat-x"). If {@code null}, will be converted to "null".
     * @return This styler instance for further chaining.
     */
    public S customProperty(String propertyName, String cssValue) {
        Args.requireNotNull(propertyName, "propertyName");
        Args.requireNotEmpty(propertyName, "propertyName");

        if (cssValue == null) {
            cssValue = "null";
        }

        Args.requireNotEmpty(cssValue, "cssValue");

        addStyle(propertyName.trim(), cssValue);
        return self();
    }

    /**
     * Abstract method implemented by concrete stylers to create the specific
     * type of {@link StyleDefinition} they are responsible for.
     * <p>
     * Subclasses must implement this method to instantiate the appropriate
     * {@code StyleDefinition} type (e.g., {@code RegionStyleDefinition}, {@code TextStyleDefinition})
     * using the provided map of collected styles.
     * </p>
     *
     * @param builtStyles An immutable (or copy) map of the CSS properties and
     *                    values collected by the builder.
     * @return The specific {@link StyleDefinition} instance.
     */
    protected abstract D createDefinition(Map<String, String> builtStyles);

    /**
     * Finalizes the styling process and builds an immutable {@link StyleDefinition}
     * object containing all the collected CSS property-value pairs.
     * <p>
     * This method transfers the styles from the builder into a {@code StyleDefinition}
     * instance, which can then be applied to one or more nodes or used to generate
     * CSS rule strings.
     * </p>
     *
     * @return An immutable {@link StyleDefinition} object representing the defined styles.
     */
    public final D build() {
        return createDefinition(new LinkedHashMap<>(styles));
    }
}