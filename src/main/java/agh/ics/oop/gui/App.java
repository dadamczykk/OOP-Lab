package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private AbstractWorldMap map;

    @Override
    public void init(){
        String[] args = getParameters().getRaw().toArray(new String[0]);

        try{
            MoveDirection[] directions = new OptionsParser().parse(args);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            map = new GrassField(10);
//            IWorldMap map = new RectangularMap(10, 5);
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {

        Vector2d small = this.map.getBoundingVectors()[0];
        Vector2d big = this.map.getBoundingVectors()[1];

        int width = 40;
        int height = 40;
        ColumnConstraints colCos = new ColumnConstraints(width);
        RowConstraints rowCos = new RowConstraints(height);
        Font hmm = new Font("Comic Sans MS", 20);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        Label l1 = new Label("y\\x");
        l1.setFont(hmm);
        GridPane.setHalignment(l1, HPos.CENTER);
        gridPane.getColumnConstraints().add(colCos);
        gridPane.getRowConstraints().add(rowCos);
        gridPane.add(l1, 0, 0);


        for (int i = small.x; i <= big.x; i++){
            gridPane.getColumnConstraints().add(colCos);
            Label l = new Label(Integer.toString(i));
            l.setFont(hmm);
            GridPane.setHalignment(l, HPos.CENTER);
            gridPane.add(l, i-small.x + 1, 0);
        }

        for (int i = small.y; i <= big.y; i++){
            gridPane.getRowConstraints().add(rowCos);
            Label l = new Label(Integer.toString(big.y - i));
//            l.setTextFill(Color.rgb(125, 125, 125));
            l.setFont(hmm);
            GridPane.setHalignment(l, HPos.CENTER);
            gridPane.add(l, 0, i - small.y + 1);
        }

        for (int i = small.y; i <= big.y; i++){
            for (int j = small.x; j <= big.x; j++){
                String str = " ";
                if (this.map.isOccupied(new Vector2d(j, i))){
                    str = (this.map.objectAt(new Vector2d(j, i))).toString();
                }
                Label l = new Label(str);
                l.setFont(hmm);
                if (str.equals("*")) { l.setTextFill(Color.rgb(16, 169, 16));}
                GridPane.setHalignment(l, HPos.CENTER);
                gridPane.add(l , j - small.x + 1, big.y - i + 1);
            }
        }
        Scene scene = new Scene(gridPane, (big.x - small.x + 3) * width,
                (big.y - small.y + 3) * height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
