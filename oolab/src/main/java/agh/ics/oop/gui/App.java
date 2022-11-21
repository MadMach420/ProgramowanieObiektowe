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

public class App extends Application implements ISimulationChangeObserver{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            IWorldMap map = new GrassField(10);
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.addObserver(this);
            engine.run();

            GridPane gridPane = new GridPane();
            drawGridPane(map, gridPane);

            Scene scene = new Scene(gridPane, 1000, 1000);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void drawGridPane(IWorldMap map, GridPane gridPane){
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        Vector2d[] boundaries = ((AbstractWorldMap)map).getMapLimits();
        int width = boundaries[1].x - boundaries[0].x + 1;
        int height = boundaries[1].y - boundaries[0].y + 1;

        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < height + 1; j++) {
                if (i == 0 && j == 0) {
                    Label label = new Label("y\\x");
                    gridPane.getColumnConstraints().add(new ColumnConstraints(40));
                    gridPane.getRowConstraints().add(new RowConstraints(40));
                    gridPane.add(label, i, j);
                    GridPane.setHalignment(label, HPos.CENTER);
                } else if (j == 0) {
                    Label label = new Label(Integer.toString(boundaries[0].x + i -1));
                    gridPane.getColumnConstraints().add(new ColumnConstraints(40));
                    gridPane.add(label, i, j);
                    GridPane.setHalignment(label, HPos.CENTER);
                } else if (i == 0) {
                    Label label = new Label(Integer.toString(boundaries[1].y - j + 1));
                    gridPane.getRowConstraints().add(new RowConstraints(40));
                    gridPane.add(label, i, j);
                    GridPane.setHalignment(label, HPos.CENTER);
                } else {
                    Vector2d position = new Vector2d(boundaries[0].x + i - 1, boundaries[1].y - j + 1);
                    Object obj = map.objectAt(position);
                    if (obj != null) {
                        GuiElementBox box = new GuiElementBox((IMapElement) obj);
                        gridPane.add(box.vBox, i, j);
                        GridPane.setHalignment(box.vBox, HPos.CENTER);
                    } else {
                        Label label = new Label("");
                        gridPane.add(label, i, j);
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
            }
        }
    }

    @Override
    public void simulationStep() {

    }
}
