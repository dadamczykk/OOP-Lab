package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {
    @Test
    public void canMoveToTest(){
        IWorldMap map = new RectangularMap(10, 10);
        assertTrue(map.canMoveTo(new Vector2d(5, 5)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map.canMoveTo(new Vector2d(15, 15)));
    }

    @Test
    public void placeTest(){
        IWorldMap map = new RectangularMap(10, 10);
        assertTrue(map.place((new Animal(map, new Vector2d(5, 5)))));
        assertFalse(map.place((new Animal(map, new Vector2d(5, 5)))));
        assertFalse(map.place((new Animal(map, new Vector2d(15, 15)))));
    }

    @Test
    public void isOccupiedTest(){
        IWorldMap map = new RectangularMap(10, 10);
        assertFalse(map.isOccupied(new Vector2d(5, 5)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertTrue(map.isOccupied(new Vector2d(5,5)));
        assertFalse(map.isOccupied(new Vector2d(15, 15)));
    }

    @Test
    public void objectAtTest(){
        IWorldMap map = new RectangularMap(10, 10);
        assertNull(map.objectAt(new Vector2d(5, 5)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertTrue(map.objectAt(new Vector2d(5, 5)) instanceof Animal);
        assertNull(map.objectAt(new Vector2d(15, 15)));
    }
}
