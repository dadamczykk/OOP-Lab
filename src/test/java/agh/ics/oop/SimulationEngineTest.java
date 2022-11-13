package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationEngineTest {

    @Test
    public void basicTest(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    public void collisionsTest(){
        String[] args = {"f", "r", "r", "l", "f", "f", "b", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(1,1), new Vector2d(1,2),
                new Vector2d(2, 2), new Vector2d(2, 1)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 1)));
    }


    @Test
    public void bordersTest(){
        String[] args = {"l", "f", "r", "b", "f", "f", "f", "b", "f", "f", "f", "b",
        "r", "r", "r", "r", "f", "f", "f", "b", "f", "f", "f", "b", "f", "f", "f", "b",  "f", "f", "f", "b",};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(1,1), new Vector2d(1,2),
                new Vector2d(2, 2), new Vector2d(2, 1)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertTrue(map.isOccupied(new Vector2d(0, 3)));
        assertTrue(map.isOccupied(new Vector2d(3, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void cannotPlaceTest(){
        String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f",
                "f", "f", "f", "f", "f", "f", "f", "f", "r", "r", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(6, 9);
        Vector2d[] positions = { new Vector2d(3,4), new Vector2d(3,8),
                new Vector2d(4, 7), new Vector2d(11, 12),
                new Vector2d(3, 8), new Vector2d(11, 12),
                new Vector2d(4, 3), new Vector2d(3, 4),
                new Vector2d(4, 7), new Vector2d(4, 7),};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(5, 8)));
        assertTrue(map.isOccupied(new Vector2d(5, 7)));
        assertTrue(map.isOccupied(new Vector2d(4, 7)));
        assertTrue(map.isOccupied(new Vector2d(4, 8)));
    }
}


