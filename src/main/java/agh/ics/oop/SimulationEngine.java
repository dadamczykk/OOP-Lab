package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.map = map;
        this.moves = moves;
        this.animals = new ArrayList<>();
        for (Vector2d position : positions){
            Animal animal = new Animal(map, position);
            if (map.place(animal)){
                animals.add(animal);
                animal.addObserver((oldPosition, newPosition) -> System.out.println("Zaobserwowano przemieszczenie "
                        + oldPosition + " -> " + newPosition)); // lambda odpowiadająca positionChanged
            }

        }
    }

    @Override
    public void run() {
        System.out.println(map);
        for (int i = 0; i < moves.length; i++){
            animals.get(i % animals.size()).move(moves[i]);
            System.out.println(map);
        }
    }

    public void runSwing(){
        System.out.println(map);
        for (int i = 0; i < moves.length; i++){
            animals.get(i % animals.size()).move(moves[i]);
            System.out.println(map);
        }
        ((RectangularMap)map).run();
    }
}
