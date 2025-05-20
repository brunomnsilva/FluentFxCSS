package com.brunomnsilva.fluentfxcss;

import com.brunomnsilva.fluentfxcss.util.CssHelper;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.Map;

// S is the styler type, D is the com.brunomnsilva.fluentfxcss.StyleDefinition type it builds
public abstract class BaseStyler<S extends BaseStyler<S, D>, D extends StyleDefinition> {
    protected final Map<String, String> styles = new LinkedHashMap<>();

    protected BaseStyler() {
        // No node in constructor now
    }

    @SuppressWarnings("unchecked")
    protected S self() {
        return (S) this;
    }

    protected void addStyle(String property, String value) {
        this.styles.put(property, value);
    }

    // --- Common Properties (same as before) ---
    public S opacity(double value) {
        addStyle("-fx-opacity", String.valueOf(value));
        return self();
    }

    public S dropShadow(Color color, double radius, double offsetX, double offsetY) {
        addStyle("-fx-effect", CssHelper.toCssDropShadow(color, radius, offsetX, offsetY));
        return self();
    }

    public S cursor(Cursor cursor) {
        addStyle("-fx-cursor", CssHelper.toCssCursor(cursor));
        return self();
    }

    public S visible(boolean value) {
        addStyle("-fx-visible", String.valueOf(value));
        return self();
    }

    // --- Terminal build method ---
    /**
     * Builds the com.brunomnsilva.fluentfxcss.StyleDefinition object from the configured styles.
     * @return A com.brunomnsilva.fluentfxcss.StyleDefinition embodying the styles.
     */
    public D build() {
        // Each concrete styler will know what kind of com.brunomnsilva.fluentfxcss.StyleDefinition to create.
        // This method might be abstract or provide a default implementation
        // if the com.brunomnsilva.fluentfxcss.StyleDefinition type 'D' can be instantiated easily.
        return createDefinition(new LinkedHashMap<>(styles)); // Pass a copy
    }

    /**
     * Abstract method to be implemented by subclasses to create the specific
     * type of com.brunomnsilva.fluentfxcss.StyleDefinition they are responsible for.
     * @param builtStyles The map of styles collected by the builder.
     * @return The specific com.brunomnsilva.fluentfxcss.StyleDefinition instance.
     */
    protected abstract D createDefinition(Map<String, String> builtStyles);
}