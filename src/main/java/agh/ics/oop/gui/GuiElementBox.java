package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {
    private final VBox box;

    public GuiElementBox(IMapElement mapElement) {
        Image img = new Image(mapElement.getImgSrc());
        ImageView view = new ImageView(img); // px
        int size = 20;
        view.setFitHeight(size);
        view.setFitWidth(size);

        Label label;

        if (mapElement instanceof Animal){
            label = new Label(mapElement.getPosition().toString());
        }
        else{
            label = new Label("trawa");
        }
        this.box = new VBox(view, label);
        this.box.setAlignment(Pos.CENTER);
    }

    public VBox getElementBox(){
        return this.box;
    }


}
