package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void everythingGoodTest(){
        String[] args = {"f", "right", "r", "l", "forward", "b", "backward", "left"};
        MoveDirection[] expectedOutput = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
        MoveDirection.LEFT};
        assertArrayEquals(expectedOutput, new OptionsParser().parse(args));
    }

    @Test
    public void exceptionTest(){
        String[] args = {"r", "l", "forward", "b", "backward", "ala", "ma", "kota"};
        IllegalArgumentException msg = assertThrows(IllegalArgumentException.class,
                () -> new OptionsParser().parse(args));
        assertEquals(msg.getMessage(), "ala nie jest poprawnym argumentem ruchu");

    }
}
