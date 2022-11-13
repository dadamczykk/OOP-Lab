package agh.ics.oop;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MapVisualizerSwing {
    private static final int tileSize = 50;
    private static final int mapStart = 80;
    private static final int tileSpace = 3;
    private final JFrame frame = new JFrame();
    private JLabel[][] tiles;
    // tiles, to pola, które będą stanowiły osie i mapę, ich zawartość będzie aktualizowana
    // zależenie od sceny
    private JLabel sceneNumber;
    private int currentScene = 0;
    private final List<String[][]> states = new ArrayList<>();
    // states to lista z poszczególnymi scenami, aby przeprowadzić wizualizację, są do niej dodawane kolejne obrazy map
    // ze zaktualizowanymi ruchami zwierząt
    private final Vector2d upperRight;

    public MapVisualizerSwing(RectangularMap map, Vector2d uppRight){
        this.upperRight = new Vector2d(uppRight.x + 1, uppRight.y + 1);
        this.states.add(new String[this.upperRight.y + 2][this.upperRight.x + 2]); // pusta scena
        for (int i = 0; i <= this.upperRight.x; i++){
            for (int j = 0; j <= this.upperRight.y; j++){
                this.states.get(this.states.size() - 1)[upperRight.y-j][i+1] = "";
            }
        }
    }

    public void addScene(RectangularMap scene){
        this.states.add(new String[this.upperRight.y + 2][this.upperRight.x + 2]);
        for (int i = 0; i <= this.upperRight.x; i++){
            for (int j = 0; j <= this.upperRight.y; j++){
                if (scene.isOccupied(new Vector2d(i, j))){
                    this.states.get(this.states.size() - 1)[upperRight.y-j][i+1] = scene.objectAt(new Vector2d(i, j)).toString();
                }
                else{
                    this.states.get(this.states.size() - 1)[upperRight.y-j][i+1] = "";
                }
            }
        }
    }
    public void run(){
        setField();
        changeScene();
    }
    private void setField(){ // ta metoda ustawia początkowy wygląd mapy
        this.frame.setSize((this.upperRight.x + 1) * (tileSize+ tileSpace) + 2 * mapStart,
                (this.upperRight.y + 1) * (tileSize + tileSpace) + 2 * mapStart);

        this.tiles = new JLabel[this.upperRight.y + 1][this.upperRight.x + 1];

        for (int i = 0; i <= this.upperRight.x; i++){
            for (int j = 0; j <= this.upperRight.y; j++){
                if (i == 0 && j == 0){
                    this.tiles[j][i] = new JLabel("y\\x", SwingConstants.CENTER);
                    this.tiles[j][i].setBackground(Color.GRAY);
                }
                else if (i == 0){
                    this.tiles[j][i] = new JLabel(String.format("%2d", this.upperRight.y - j), SwingConstants.CENTER);
                    this.tiles[j][i].setBackground(Color.GRAY);
                }
                else if (j == 0){
                    this.tiles[j][i] = new JLabel(String.format("%2d", i - 1), SwingConstants.CENTER);
                    this.tiles[j][i].setBackground(Color.GRAY);
                }
                else {
                    this.tiles[j][i] = new JLabel("", SwingConstants.CENTER);
                    this.tiles[j][i].setBackground(new Color(255, 191, 221));
                }
                this.tiles[j][i].setOpaque(true);
                this.tiles[j][i].setBounds(mapStart + (tileSize + tileSpace) * i,
                        mapStart + (tileSize + tileSpace) * j,tileSize, tileSize);
                this.frame.add(tiles[j][i]);
            }
        }
        this.sceneNumber = new JLabel(String.format("Scena %2d", 0), SwingConstants.CENTER);
        this.sceneNumber.setBounds(0, 0, mapStart, mapStart);
        this.frame.add(this.sceneNumber);

        JButton close = new JButton("Wyjscie");

        close.addActionListener(e -> System.exit(0));

        close.setBounds((2* mapStart + (this.upperRight.x + 1) * (tileSize + tileSpace) - 100)/2,
                (this.upperRight.y + 1) * (tileSize+ tileSpace) + mapStart, 100, 20);

        this.frame.add(close);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }

    private ImageIcon getResourceImage(String resourceName){
        URL resource = getClass().getClassLoader().getResource(resourceName);
        if (resource == null){
            return null;
        }
        ImageIcon toFormat = new ImageIcon(resource);
        Image img = toFormat.getImage();
        Image finalImg = img.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
        return new ImageIcon(finalImg);
    }

    private void changeScene() { // ta metoda odpowiada za obsługę mapy - przemieszczanie się pomiędzy scenami
        JButton prev = new JButton("poprzednia scena");
        JButton next = new JButton("nastepna scena");
        prev.setBounds(mapStart, 30, ((tileSize + tileSpace) * (this.upperRight.x + 1))/2, 25);
        next.setBounds(((tileSize + tileSpace) * (this.upperRight.x + 1))/2 + mapStart , 30,
                (this.upperRight.x+1) * (tileSize + tileSpace)/2, 25);
        this.frame.add(prev);
        this.frame.add(next);

        next.addActionListener(e -> {
            this.currentScene = min(this.currentScene+1, states.size() - 1);
            this.sceneNumber.setText(String.format("Scena %2d", currentScene));
            for (int i = 1; i <= this.upperRight.x; i++){
                for (int j = 1; j <= this.upperRight.y; j++){
                    switch (this.states.get(this.currentScene)[j][i]){
                        case "v" -> this.tiles[j][i].setIcon(getResourceImage("catDown.png"));
                        case "^" -> this.tiles[j][i].setIcon(getResourceImage("catUp.png"));
                        case ">" -> this.tiles[j][i].setIcon(getResourceImage("catRight.png"));
                        case "<" -> this.tiles[j][i].setIcon(getResourceImage("catLeft.png"));
                        default -> this.tiles[j][i].setIcon(null);
                    }
//                    if (this.states.get(this.currentScene)[j][i].equals("")) {
//                        this.tiles[j][i].setText(this.states.get(this.currentScene)[j][i]);
//                        this.tiles[j][i].setIcon(null);
//                    }
//                    else {
//                        this.tiles[j][i].setIcon(getResourceImage("cat.png"));
//                    }
                }
            }
        });
        prev.addActionListener(e -> {
            this.currentScene = max(this.currentScene-1, 0);
            this.sceneNumber.setText(String.format("Scena %2d", currentScene));
            for (int i = 1; i <= this.upperRight.x; i++){
                for (int j = 1; j <= this.upperRight.y; j++){
//                    this.tiles[j][i].setText(this.states.get(this.currentScene)[j][i]);
                    switch (this.states.get(this.currentScene)[j][i]){
                        case "v" -> this.tiles[j][i].setIcon(getResourceImage("catDown.png"));
                        case "^" -> this.tiles[j][i].setIcon(getResourceImage("catUp.png"));
                        case ">" -> this.tiles[j][i].setIcon(getResourceImage("catRight.png"));
                        case "<" -> this.tiles[j][i].setIcon(getResourceImage("catLeft.png"));
                        default -> this.tiles[j][i].setIcon(null);
                    }
                }
            }
        });
    }

}


