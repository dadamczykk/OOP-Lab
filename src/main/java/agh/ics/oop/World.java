package agh.ics.oop;

public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        IWorldMap map = new GrassField(10);
//        IWorldMap map = new RectangularMap(10, 5);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

    }
}