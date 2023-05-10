package com.physicsengine;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GraphicsWorld {
    private static final double scale = 40.0; // pixles per meter
    private static final double windowHeight = 600.0;

    private Rectangle blockGraphic;
    private Circle circleGraphic;
    private Line ground;
    private Label positionLabel;

    public GraphicsWorld(PhysicsObject block) {
        blockGraphic = new Rectangle(block.getX() * scale, windowHeight - block.getY() * scale - scale, scale, scale);
        circleGraphic = new Circle(scale);
        circleGraphic.setFill(Color.RED);
        circleGraphic.setCenterX(200);
        circleGraphic.setCenterY(200);

        ground = new Line(0, windowHeight, 800, windowHeight);

        positionLabel = new Label();
        positionLabel.setLayoutX(block.getX() * scale);
        positionLabel.setLayoutY(block.getY() * scale - scale);

        GridOverlay grid = new GridOverlay(windowHeight, scale);
        new Pane(grid.getGrid(), blockGraphic, circleGraphic, ground, positionLabel);
    }

    public Rectangle getBlockGraphic() {
        return blockGraphic;
    }

    public Circle getCircleGraphic() {
        return circleGraphic;
    }

    public Line getGround() {
        return ground;
    }

    public void update(PhysicsObject block) {
        double epsilon = 0.0001;
        if (Math.abs(block.getVy()) > epsilon || Math.abs(block.getVx()) > epsilon) {
            blockGraphic.setY(block.getY() * scale - scale);
            positionLabel.setLayoutY(block.getY() * scale - scale);
            positionLabel.setLayoutX(block.getX() * scale + 40);
            positionLabel.setText(String.format("(%.2f, %.2f)", block.getX(), block.getY()));
        }
    }

    public Pane getPane() {
        Pane root = new Pane(blockGraphic, circleGraphic, ground, positionLabel);
        return root;
    }
}
