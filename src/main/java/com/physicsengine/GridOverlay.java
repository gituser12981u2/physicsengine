package com.physicsengine;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class GridOverlay {
    private Group grid;

    public GridOverlay(double size, double step) {
        grid = new Group();

        for (int i = 0; i <= size; i += step) {
            Line hLine = new Line(0, i, size, i);
            Line vLine = new Line(i, 0, i, size);

            grid.getChildren().addAll(hLine, vLine);
        }
    }

    public Group getGrid() {
        return grid;
    }
}
