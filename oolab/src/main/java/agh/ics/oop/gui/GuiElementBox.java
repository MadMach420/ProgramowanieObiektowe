package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    protected VBox vBox;

    public GuiElementBox(IMapElement element) {
        try {
            Image image = new Image(new FileInputStream(element.getImageUrl()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label = new Label(element.getPosition().toString());
            this.vBox = new VBox(imageView, label);
            vBox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e) {
            System.out.println("File representing the element not found");
        }
    }
}
