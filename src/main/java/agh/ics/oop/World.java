package agh.ics.oop;

import java.util.Arrays;

public class World {


    public static void main(String[] args) {
        System.out.println("system wystartowal");

//        Animal testAnimal = new Animal();
//        testAnimal.move(MoveDirection.RIGHT);
//        testAnimal.move(MoveDirection.FORWARD);
//        testAnimal.move(MoveDirection.FORWARD);
//        testAnimal.move(MoveDirection.FORWARD);
//        System.out.println(testAnimal);

        Animal finalAnimal = new Animal();
        MoveDirection[] commands = new OptionsParser().parse(args);
        System.out.println(Arrays.toString(commands));
        for (MoveDirection command : commands) {
            finalAnimal.move(command);
        }
        System.out.println(finalAnimal);
        System.out.println("system zakonczyl dzialanie");

    }
}
