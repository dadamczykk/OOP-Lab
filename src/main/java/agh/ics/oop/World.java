package agh.ics.oop;

public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };


//         Odkomentować, aby włączyć wizualizację przy pomocy MapVisualizer (domyślna wersja)
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();


//        Wizualizacja przy pomocy zewnętrznego widgetu napisana przy użyciu biblioteki Swing (klasa MapVisualizerSwing)
//        Zakomentować, gdy sprawdzana jest tylko wizualizacja przy pomocy klasy MapVisualizer

        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.runSwing();


    }
}