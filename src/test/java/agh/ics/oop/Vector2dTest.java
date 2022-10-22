package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            assertEquals(vec1.equals(vec2), testx1 == testx2 && testy1 == testy2);
        }
        for(int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testy1 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx1, testy1);
            assertTrue(vec1.equals(vec2));
        }
    }
    @Test
    public void toStringTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx = rand.nextInt();
            int testy = rand.nextInt();
            Vector2d vec = new Vector2d(testx, testy);
            assertEquals(vec.toString(), "("+testx+","+testy+")");
        }
    }

    @Test
    public void precedesTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d vec3 = new Vector2d(testx1, testy2);
            Vector2d vec4 = new Vector2d(testx2, testy1);
            assertEquals(vec1.precedes(vec2), testx1<=testx2 && testy1<=testy2);
            assertEquals(vec1.precedes(vec3), testy1<=testy2);
            assertEquals(vec1.precedes(vec4), testx1<=testx2);
            assertTrue(vec1.precedes(vec1));
        }
    }

    @Test
    public void followsTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d vec3 = new Vector2d(testx1, testy2);
            Vector2d vec4 = new Vector2d(testx2, testy1);
            assertEquals(vec1.follows(vec2), testx1>=testx2 && testy1>=testy2);
            assertEquals(vec1.follows(vec3), testy1>=testy2);
            assertEquals(vec1.follows(vec4), testx1>=testx2);
            assertTrue(vec1.follows(vec1));
        }
    }

    @Test
    public void upperRightTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d upperRight = vec1.upperRight(vec2);
            Vector2d correctUpperRight = new Vector2d(Math.max(testx1, testx2), Math.max(testy1, testy2));
            assertEquals(upperRight, correctUpperRight);
        }
    }

    @Test
    public void lowerLeftTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d lowerLeft = vec1.lowerLeft(vec2);
            Vector2d correctLowerLeft = new Vector2d(Math.min(testx1, testx2), Math.min(testy1, testy2));
            assertEquals(lowerLeft, correctLowerLeft);
        }
    }

    @Test
    public void addTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d added = vec1.add(vec2);
            Vector2d correctAdded = new Vector2d(testx1 + testx2, testy1 + testy2);
            assertEquals(added, correctAdded);
        }
    }

    @Test
    public void substractTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testx2 = rand.nextInt();
            int testy1 = rand.nextInt();
            int testy2 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d vec2 = new Vector2d(testx2, testy2);
            Vector2d subtracted = vec1.subtract(vec2);
            Vector2d correctSubtracted = new Vector2d(testx1 - testx2, testy1 - testy2);
            assertEquals(subtracted, correctSubtracted);
        }
    }

    @Test
    public void oppositeTest(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int testx1 = rand.nextInt();
            int testy1 = rand.nextInt();
            Vector2d vec1 = new Vector2d(testx1, testy1);
            Vector2d opposite = new Vector2d(-testx1, -testy1);
            assertEquals(vec1.opposite(), opposite);
        }
    }



}
