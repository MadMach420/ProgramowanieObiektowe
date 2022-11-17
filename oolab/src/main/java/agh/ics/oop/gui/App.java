package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            IWorldMap map = new GrassField(10);
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);

            Vector2d[] boundaries = ((AbstractWorldMap)map).getMapLimits();
            int width = boundaries[1].x - boundaries[0].x + 1;
            int height = boundaries[1].y - boundaries[0].y + 1;

            GridPane gridPane = new GridPane();
            gridPane.setGridLinesVisible(true);

            for (int i = 0; i < width + 1; i++) {
                for (int j = 0; j < height + 1; j++) {
                    Label label;

                    if (i == 0 && j == 0) {
                        label = new Label("y\\x");
                        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
                        gridPane.getRowConstraints().add(new RowConstraints(30));
                    } else if (j == 0) {
                        label = new Label(Integer.toString(boundaries[0].x + i -1));
                        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
                    } else if (i == 0) {
                        label = new Label(Integer.toString(boundaries[1].y - j + 1));
                        gridPane.getRowConstraints().add(new RowConstraints(30));
                    } else {
                        Object obj = map.objectAt(new Vector2d(boundaries[0].x + i - 1, boundaries[1].y - j + 1));
                        if (obj != null) {
                            label = new Label(obj.toString());
                        } else {
                            label = new Label("");
                        }
                    }

                    gridPane.add(label, i, j);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }

            Scene scene = new Scene(gridPane, 1000, 1000);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
