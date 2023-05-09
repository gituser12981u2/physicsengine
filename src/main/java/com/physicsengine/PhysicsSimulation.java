package com.physicsengine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PhysicsSimulation extends Application {
    private static final double windowHeight = 600.0;

    private long lastFrameTime;

    private PhysicsWorld physicsWorld;
    private GraphicsWorld graphicsWorld;

    @Override
    public void start(Stage primaryStage) {
        physicsWorld = new PhysicsWorld();
        graphicsWorld = new GraphicsWorld(physicsWorld.getBlock());

        Pane root = graphicsWorld.getPane();
        Scene scene = new Scene(root, 800, windowHeight);

        primaryStage.setScene(scene);
        primaryStage.show();

        lastFrameTime = System.nanoTime();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = (now - lastFrameTime) / 1e9; // convert from nano to seconds
                lastFrameTime = now;

                physicsWorld.update(dt);
                graphicsWorld.update(physicsWorld.getBlock());
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
