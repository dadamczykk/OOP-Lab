package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.FileNotFoundException;


public class App extends Application implements IMotionObserver {

    private AbstractWorldMap map;
    private Vector2d smallBoundary;
    private Vector2d bigBoundary;
    private final GridPane mainGrid = new GridPane();
    private final int width = 40;
    private final int height = 40;
    private SimulationEngine engine;
    private Stage stage;

    @Override
    public void init(){
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        this.engine = new SimulationEngine(map, positions);
        this.engine.addObserverToAnimals(this);
        this.engine.setMoveDelay(500);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.motionDetected();
        Font hmmm = new Font("Comic Sans MS Bold", 12);

        Button startButton = new Button("Start");
        startButton.setDefaultButton(true);
        startButton.setFont(hmmm);
        startButton.setWrapText(true);

        Label header = new Label("""
                Witaj w grze!
                 Wpisz ruchy do ponizszego pola, kliknij enter i obserwuj!
                Mozesz wpisac: f l r b oddzielone od siebie spacjami :)""");
        header.setTextAlignment(TextAlignment.CENTER);
        header.setFont(hmmm);
        header.setWrapText(true);

        TextField inputArgs = new TextField();
        inputArgs.setAlignment(Pos.CENTER);

        HBox idkBox = new HBox(inputArgs, startButton);
        idkBox.setAlignment(Pos.CENTER);

        Label info = new Label();
        info.setFont(hmmm);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setWrapText(true);

        startButton.setOnAction(event -> {
            try {
                String[] input = inputArgs.getText().split(" ");
                MoveDirection[] directions = new OptionsParser().parse(input);
                this.engine.setMoves(directions);
                Thread sth = new Thread(this.engine);
                sth.start();
                info.setText("Wprowadzono poprawne ruchy!");
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                info.setText("Mamy problem: " + e.getMessage());
            }
        });

        mainGrid.setAlignment(Pos.CENTER);
        VBox hmm = new VBox(header, idkBox, mainGrid, info);
        hmm.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hmm);
        primaryStage.setScene(scene);
        this.setStageDim();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }
    private void setStageDim(){
        try {
            this.updateBoundaries();
            this.stage.setWidth((this.bigBoundary.x - this.smallBoundary.x + 4) * width * 1.1);
            this.stage.setHeight((this.bigBoundary.y - this.smallBoundary.y + 6) * height * 1.1);
        }
        catch (NullPointerException e){
            System.out.println("Blad, stage nie zostal zainicjowany!!!\n" + e.getMessage());
        }
    }
    public void updateBoundaries(){
        this.smallBoundary = this.map.getBoundingVectors()[0];
        this.bigBoundary = this.map.getBoundingVectors()[1];
    }


    public void updateScene() throws FileNotFoundException {
        this.mainGrid.getChildren().clear();
        this.updateBoundaries();
        ColumnConstraints colCos = new ColumnConstraints(width);
        RowConstraints rowCos = new RowConstraints(height);
        Font hmm = new Font("Comic Sans MS", 20);
        Label l1 = new Label("y\\x");
        l1.setFont(hmm);
        GridPane.setHalignment(l1, HPos.CENTER);
        this.mainGrid.getColumnConstraints().add(colCos);
        this.mainGrid.getRowConstraints().add(rowCos);
        this.mainGrid.add(l1, 0, 0);

        for (int i = this.smallBoundary.x; i <= this.bigBoundary.x; i++){
            this.mainGrid.getColumnConstraints().add(colCos);
            Label l = new Label(Integer.toString(i));
            l.setFont(hmm);
            GridPane.setHalignment(l, HPos.CENTER);
            this.mainGrid.add(l, i-this.smallBoundary.x + 1, 0);
        }

        for (int i = this.smallBoundary.y; i <= this.bigBoundary.y; i++){
            this.mainGrid.getRowConstraints().add(rowCos);
            Label l = new Label(Integer.toString(this.bigBoundary.y - i + this.smallBoundary.y));
            l.setFont(hmm);
            GridPane.setHalignment(l, HPos.CENTER);
            this.mainGrid.add(l, 0, i - this.smallBoundary.y +1);
        }

        for (int i = this.smallBoundary.y; i <= this.bigBoundary.y; i++){
            for (int j = this.smallBoundary.x; j <= this.bigBoundary.x; j++){
                if (this.map.isOccupied(new Vector2d(j, i))){
                    GuiElementBox box = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(j, i)));
                    VBox vBox = box.getElementBox();
                    this.mainGrid.add(vBox,
                            j + 1 - smallBoundary.x,
                            this.bigBoundary.y - i + 1);
                }
            }
        }


    }

    @Override
    public void motionDetected() {
        Platform.runLater(() -> {
            try {
                this.mainGrid.getColumnConstraints().clear();
                this.mainGrid.getRowConstraints().clear();
                this.mainGrid.setGridLinesVisible(false);
                updateScene();
                this.mainGrid.setGridLinesVisible(true);
                this.setStageDim();


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
