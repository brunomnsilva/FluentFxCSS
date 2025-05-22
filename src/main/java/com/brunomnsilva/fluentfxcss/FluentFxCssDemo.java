package com.brunomnsilva.fluentfxcss;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets; // For Data URI
import java.util.Arrays;
import java.util.Base64;                 // For Data URI

/**
 * Demo application for FluentFxCSS.
 *
 * @author brunomnsilva
 */
public class FluentFxCssDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        // --- Instantiate a gradient to use later ---
        LinearGradient lg = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                Arrays.asList(
                        new Stop(0, Color.WHITE),
                        new Stop(1, Color.DARKGRAY)
                )
        );

        // --- Define Reusable Style Definitions ---
        System.out.println("--- Defining Styles ---");

        // Style for the main container/root pane
        StyleDefinition rootPaneStyle = FluentFxCss.paneStyle()
                .backgroundColor(lg)
                .padding(20) // Assuming PX default
                .shape("M50,0 A50,50 0 1 1 49.9,0 Z") // Circle
                .build();

        // A common element style
        StyleDefinition commonStyle = FluentFxCss.shapeStyle()
                .cursor(Cursor.HAND)
                .strokeWidth(2.5)
                .dropShadow(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0,0,0,0.3), 10, 0.2, 3, 3))
                .build();

        // Style for a primary shape
        StyleDefinition circleStyle = FluentFxCss.shapeStyle()
                .fill(Color.DARKSLATEGRAY)
                .opacity(0.9)
                .stroke(Color.DARKGOLDENROD)
                .strokeDashArray(4, 4)
                .build()
                .mergeWith(commonStyle); // Demonstrate merging for base interactive properties


        // Style for a secondary shape with more custom properties
        StyleDefinition rectangleStyle = FluentFxCss.shapeStyle()
                .fill(Color.MEDIUMSEAGREEN)
                .stroke(Color.DARKGREEN)
                .strokeWidth(0.2, UnitValue.CM) // Demonstrate unit usage
                .opacity(0.75)
                .customProperty("-fx-arc-height", "30px") // Explicit unit for clarity
                .customProperty("-fx-arc-width", "30px")
                .rotate(15) // Rotate the rectangle
                .build()
                .mergeWith(commonStyle);

        // Style for text elements
        DropShadow glow = new DropShadow(BlurType.GAUSSIAN, Color.WHITE, 5, 1, 0, 0);
        StyleDefinition textStyle = FluentFxCss.textStyle()
                .fontFamily("Verdana") // Common font
                .fontWeight(FontWeight.BOLD)
                .fontSize(22, UnitValue.PT) // Demonstrate unit usage for font
                .fill(Color.MIDNIGHTBLUE)
                .underline(true)
                .dropShadow(glow)
                .build();

        // Style for decorative lines
        StyleDefinition lineStyle = FluentFxCss.shapeStyle()
                .strokeWidth(4)
                .strokeLineCap(StrokeLineCap.ROUND)
                .stroke(Color.SLATEGRAY)
                .strokeDashArray(10, 8) // Simple dash array
                .opacity(0.6)
                .build();

        System.out.println("Circle Style CSS: " + circleStyle.toCssInline());
        System.out.println("Rectangle Style CSS: " + rectangleStyle.toCssInline());
        System.out.println("Title Text Style CSS: " + textStyle.toCssInline());
        System.out.println("Line Style CSS: " + lineStyle.toCssInline());


        // --- Create UI Elements ---
        Text titleText = new Text("FluentFxCSS Demo");
        Circle circle = new Circle(50); // Radius 50
        Rectangle rectangle = new Rectangle(100, 70); // Width 100, Height 70
        Line line = new Line(0, 0, 150, 0); // Horizontal line

        // --- Apply Inline Styles ---
        System.out.println("\n--- Applying Inline Styles ---");
        textStyle.applyTo(titleText);
        circleStyle.applyTo(circle);
        rectangleStyle.applyTo(rectangle);
        lineStyle.applyTo(line);

        // --- Demonstrate CSS Class Generation & Usage ---
        System.out.println("\n--- Generating CSS Class Rules ---");

        // Style for a 'button-like' element on hover
        StyleDefinition hoverEffectStyle = FluentFxCss.shapeStyle()
                .opacity(1.0) // Fully opaque on hover
                .fill(Color.LIGHTCORAL) // Change fill color
                .build();

        String baseButtonClassCss = commonStyle.toCssClass("interactive-button");
        String hoverButtonClassCss = hoverEffectStyle.toCssPseudoClass("interactive-button", "hover");

        System.out.println(baseButtonClassCss);
        System.out.println(hoverButtonClassCss);

        // Create a stylesheet string
        String dynamicStylesheetContent = baseButtonClassCss + "\n" + hoverButtonClassCss;
        String dataUriCss = "data:text/css;base64," + Base64.getEncoder().encodeToString(dynamicStylesheetContent.getBytes(StandardCharsets.UTF_8));

        // Create an element that will use this class
        Rectangle styledButton = new Rectangle(120, 40);
        styledButton.getStyleClass().add("interactive-button"); // Add the style class

        // --- 5. Layout UI Elements ---
        VBox rootLayout = new VBox(30); // Use VBox for better spacing
        rootLayout.setAlignment(Pos.CENTER);
        rootPaneStyle.applyTo(rootLayout); // Apply root style to VBox

        VBox shapesBox = new VBox(20, circle, rectangle, styledButton); // Group shapes
        shapesBox.setAlignment(Pos.CENTER);

        rootLayout.getChildren().addAll(titleText, shapesBox, line);

        // --- Scene and Stage Setup ---
        Scene scene = new Scene(rootLayout, 500, 500);
        scene.getStylesheets().add(dataUriCss); // Add the dynamically generated stylesheet

        primaryStage.setTitle("FluentFxCSS Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
