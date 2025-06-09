/**
 * A fluent, type-safe CSS builder for JavaFX.
 */
module com.brunomnsilva.fluentfxcss {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;

    exports com.brunomnsilva.fluentfxcss;
    exports com.brunomnsilva.fluentfxcss.definitions;
    exports com.brunomnsilva.fluentfxcss.stylers;
    exports com.brunomnsilva.fluentfxcss.stylers.concrete;
    exports com.brunomnsilva.fluentfxcss.enums;
    exports com.brunomnsilva.fluentfxcss.util;
}