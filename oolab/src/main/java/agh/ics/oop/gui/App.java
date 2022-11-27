package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application implements ISimulationChangeObserver{
    Stage stage;
    IWorldMap map;
    HBox hBox;
    int moveDelay = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            init();
            Scene scene = new Scene(hBox, 1000, 1000);
            stage.setScene(scene);
            stage.show();
            drawStage();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        map = new GrassField(10);
//        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        SimulationEngine engine = new SimulationEngine(map, positions);
        engine.addObserver(this);


        TextField textField = new TextField();
        Button button = new Button("Start");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MoveDirection[] directions = OptionsParser.parse(textField.getText().split(" +"));
                    engine.setDirections(directions);
                    Thread engineThread = new Thread(engine);
                    engineThread.start();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        });


        VBox controlsVBox = new VBox(20, textField, button);
        hBox = new HBox(20, controlsVBox, new Label(""));
    }

    public void drawStage(){
        GridPane gridPane = new GridPane();
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

        hBox.getChildren().remove(1);
        hBox.getChildren().add(1, gridPane);
    }

    @Override
    public void simulationStep() {
        Platform.runLater(this::drawStage);
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted");
        }
    }
}
