# FluentFxCSS

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
<!-- Optional: Add build status badge -->

FluentFxCSS provides a fluent, type-safe builder API to programmatically construct CSS styles for JavaFX `Node`, `Region`, `Shape`, and `Text` elements. Simplify your JavaFX styling with readable and maintainable code.

![demo gif](assets/demo.gif)

## Key Features

*   **Fluent & Type-Safe:** Intuitive chained methods with compile-time safety.
*   **Reusable Styles:** Build `StyleDefinition` objects to apply or merge styles.
*   **CSS Generation:** Apply styles inline or generate CSS class/pseudo-class strings.
*   **Unit Support:** Specify units (PX, EM, PT, CM, etc.) for many length properties.

## Installation

**Maven Example:**

```xml
<dependency>
    <groupId>com.brunomnsilva</groupId>
    <artifactId>fluentfxcss</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle Example:**

```gradle
implementation 'com.brunomnsilva:fluentfxcss:1.0.0'
```

## Quick Start

```java
import com.example.fluentfxcss.FluentFxCss;
import com.example.fluentfxcss.definition.StyleDefinition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

Pane myPane = new Pane();

StyleDefinition paneLook = FluentFxCss.paneStyle()
    .padding(10) // Defaults to PX
    .backgroundColor(Color.LIGHTBLUE)
    .build();

paneLook.applyTo(myPane);
```

## Supported CSS Properties (Fluent API)

The following CSS properties have direct fluent methods in their respective stylers. Styler methods are inherited, so `RegionStyler` includes all `NodeStyler` methods, etc.

| Styler                                 | CSS Property              | Example Fluent Method(s)                                  |
|----------------------------------------|---------------------------|-----------------------------------------------------------|
| **NodeStyler** (Base for all)          |                           |                                                           |
|                                        | `-fx-opacity`             | `.opacity(double value)`                                  |
|                                        | `-fx-effect` (DropShadow) | `.dropShadow(BlurType, Color, radius, spread, offX, offY)` |
|                                        | `-fx-effect` (InnerShadow)| `.innerShadow(BlurType, Color, radius, choke, offX, offY)`|
|                                        | `-fx-cursor`              | `.cursor(Cursor cursor)`                                  |
|                                        | `-fx-visible`             | `.visible(boolean value)`                                 |
|                                        | `-fx-rotate`              | `.rotate(double degrees)`                                 |
| **RegionStyler**    |                         |                                                           |
|                                        | `-fx-background-color`    | `.backgroundColor(Paint paint)`                           |
|                                        | `-fx-background-radius`   | `.backgroundRadius(double radius)` / `..(UnitValue, ...)` |
|                                        | `-fx-padding`             | `.padding(double allSides)` / `..(UnitValue, ...)`        |
|                                        | `-fx-border-color`        | `.borderColor(Color color)`                               |
|                                        | `-fx-border-style`        | `.borderStyle(BorderStyleValue style)` / `..(styles...)`   |
|                                        | `-fx-border-width`        | `.borderWidth(double width)` / `..(UnitValue, ...)`       |
|                                        | `-fx-border-radius`       | `.borderRadius(double radius)` / `..(UnitValue, ...)`     |
|                                        | `-fx-shape`               | `.shape(String svgPath)`                                  |
|                                        | `-fx-pref-width`          | `.prefWidth(double value)` / `..(UnitValue, ...)`         |
|                                        | `-fx-pref-height`         | `.prefHeight(double value)` / `..(UnitValue, ...)`        |
| **ShapeStyler**   |                          |                                                           |
|                                        | `-fx-fill`                | `.fill(Paint paint)`                                      |
|                                        | `-fx-stroke`              | `.stroke(Paint paint)`                                    |
|                                        | `-fx-stroke-width`        | `.strokeWidth(double value)` / `..(UnitValue, ...)`       |
|                                        | `-fx-stroke-type`         | `.strokeType(StrokeType type)`                            |
|                                        | `-fx-stroke-dash-array`   | `.strokeDashArray(Integer... size)`                       |
|                                        | `-fx-stroke-dash-offset`  | `.strokeDashOffset(double value)`                         |
|                                        | `-fx-stroke-line-cap`     | `.strokeLineCap(StrokeLineCap cap)`                       |
|                                        | `-fx-stroke-line-join`    | `.strokeLineJoin(StrokeLineJoin join)`                    |
|                                        | `-fx-stroke-miter-limit`  | `.strokeMiterLimit(double value)`                         |
|                                        | `-fx-smooth`              | `.smooth(boolean value)`                                  |
| **TextStyler**                         |                          |                                                           |
|                                        | `-fx-font-family`         | `.fontFamily(String family)`                              |
|                                        | `-fx-font-size`           | `.fontSize(double size)` (defaults to PT)                 |
|                                        | `-fx-font-weight`         | `.fontWeight(FontWeight weight)`                          |
|                                        | `-fx-font-style`          | `.fontStyle(FontPosture posture)`                         |
|                                        | `-fx-font` (shorthand)    | `.font(Font font)`                                        |
|                                        | `-fx-fill` (via Shape)    | `.fill(Paint paint)` or `.textFill(Paint paint)` (alias)  |
|                                        | `-fx-font-smoothing-type` | `.smoothing(FontSmoothingType type)`                      |
|                                        | `-fx-underline`           | `.underline(boolean value)`                               |
|                                        | `-fx-strikethrough`       | `.strikethrough(boolean value)`                           |
|                                        | `-fx-text-alignment`      | `.alignment(TextAlignment alignment)`                     |

**Note:** Many methods have overloads for different numbers of arguments (e.g., padding, border radius/width) and for specifying units with `UnitValue`.

For any other CSS properties not listed above, or for more complex values, you can use the fallback method available on all stylers:
```java
myStyler.customProperty("-fx-your-css-property", "your-css-value");
```

## Further Information

For more detailed API documentation, advanced usage examples, and information on building and using `StyleDefinition` objects (including CSS class generation and merging), please refer to the [Project Wiki](link/to/wiki).

## Contributing

Contributions are welcome! 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

