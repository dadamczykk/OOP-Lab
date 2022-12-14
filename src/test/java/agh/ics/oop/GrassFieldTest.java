package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void canMoveToTest(){
        GrassField map = new GrassField(10);
        assertTrue(map.canMoveTo(new Vector2d(5, 5)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(150, 150)));
        map.placeGrass(new Grass(new Vector2d(11, 11)));
        assertTrue(map.canMoveTo(new Vector2d(11, 11)));
    }

    @Test
    public void placeTest(){
        GrassField map = new GrassField(10);
        assertTrue(map.place((new Animal(map, new Vector2d(5, 5)))));
        IllegalArgumentException message = assertThrows(IllegalArgumentException.class,
                () -> map.place(new Animal(map, new Vector2d(5,5))));
        assertEquals(message.getMessage(), "(5,5) jest niepoprawną pozycją");
//        assertFalse(map.place((new Animal(map, new Vector2d(5, 5)))));
        map.placeGrass(new Grass(new Vector2d(15, 15)));
        assertTrue(map.place(new Animal(map, new Vector2d(15, 15))));
    }

    @Test
    public void isOccupiedTest(){
        GrassField map = new GrassField(10);
        // test zmodyfikowany względem poprzedniego commita - poniższa linijka powodowała błędy
        assertFalse(map.isOccupied(new Vector2d(10, 10)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertTrue(map.isOccupied(new Vector2d(5,5)));
        assertFalse(map.isOccupied(new Vector2d(15, 15)));
        map.placeGrass(new Grass(new Vector2d(150,100)));
        assertTrue(map.isOccupied(new Vector2d(150, 100)));
    }

    @Test
    public void objectAtTest(){
        GrassField map = new GrassField(10);
        assertNull(map.objectAt(new Vector2d(21, 12)));
        map.place(new Animal(map, new Vector2d(5, 5)));
        assertTrue(map.objectAt(new Vector2d(5, 5)) instanceof Animal);
        map.placeGrass(new Grass(new Vector2d(15, 15)));
        assertTrue(map.objectAt(new Vector2d(15, 15)) instanceof Grass);
        map.place(new Animal(map, new Vector2d(15, 15)));
        assertTrue(map.objectAt(new Vector2d(15,15)) instanceof Animal);

    }
}
