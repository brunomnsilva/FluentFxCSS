package com.brunomnsilva.fluentfxcss;

import com.brunomnsilva.fluentfxcss.definitions.StyleDefinition;
import com.brunomnsilva.fluentfxcss.enums.BorderStyleValue;
import com.brunomnsilva.fluentfxcss.enums.UnitValue;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExampleApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane as the root layout
        Pane root = new Pane();

        StyleDefinition paneStyle = FluentFxCss.paneStyle()
                .cursor(Cursor.CLOSED_HAND)
                .backgroundColor(Color.PINK)
                .backgroundRadius(10)
                .borderStyle(BorderStyleValue.DASHED)
                .borderRadius(10)
                .borderWidth(3)
                .build();

        StyleDefinition shapeStyle1 = FluentFxCss.shapeStyle()
                .fill(Color.CORNFLOWERBLUE)
                .stroke(Color.DARKBLUE)
                .strokeWidth(2)
                .innerShadow(Color.WHITE, 20, 0, 0)
                .build();

        StyleDefinition shapeStyle2 = FluentFxCss.shapeStyle()
                .fill(Color.LIGHTGREEN)
                .stroke(Color.GREEN)
                .strokeWidth(0.2, UnitValue.CM)
                .opacity(0.5)
                .customProperty("-fx-arc-height", "20")
                .customProperty("-fx-arc-width", "20")
                .customProperty("-fx-rotate", "45")
                .build();

        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.WHITE, 5, 1, 0, 0);
        StyleDefinition textStyle = FluentFxCss.textStyle()
                .fontFamily("Arial")
                .fontSize(24)
                .fill(Color.DARKMAGENTA)
                .underline(true)
                .dropShadow(shadow)
                .build();

        StyleDefinition lineStyle = FluentFxCss.shapeStyle()
                .strokeWidth(5)
                .strokeLineCap(StrokeLineCap.ROUND)
                .stroke(Color.BLACK)
                .fill(Color.TRANSPARENT)
                .strokeDashArray(2, 15, 4, 15)
                .opacity(0.8)
                .build();

        // Create a Circle
        Circle circle = new Circle(100, 100, 50);
        // Create a Rectangle
        Rectangle rectangle = new Rectangle(200, 150, 120, 60);
        // Create a Text element
        Text text = new Text(20, 250, "Hello JavaFX!");
        // Create a Line element
        Line line = new Line(20, 20, 160, 20);

        // Apply styles
        paneStyle.applyTo(root);
        textStyle.applyTo(text);
        shapeStyle1.applyTo(circle);
        shapeStyle2.applyTo(rectangle);
        lineStyle.applyTo(line);

        // Merge styles
        StyleDefinition merged = shapeStyle1.mergeWith(shapeStyle2);
        System.out.println(shapeStyle1);
        System.out.println(shapeStyle2);
        System.out.println(merged);

        // Create CSS pseudo-class
        String pseudoClass = merged.toCssPseudoClass("vertex", "hover");
        System.out.println(pseudoClass);

        // Add shapes and text to the pane
        root.getChildren().addAll(circle, rectangle, text, line);

        // Create a Scene with the Pane
        Scene scene = new Scene(root, 400, 300);

        // Configure and show the stage
        primaryStage.setTitle("FluentFxCSS Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}