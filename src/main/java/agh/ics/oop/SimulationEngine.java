package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{

    private MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();
    private int moveDelay = 600;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this(map, positions);
        setMoves(moves);


    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions){
        this.map = map;
        for (Vector2d position : positions){
            Animal animal = new Animal(map, position);
            if (map.place(animal)){
                animals.add(animal);
                animal.addObserver((oldPosition, newPosition) -> System.out.println("Zaobserwowano przemieszczenie "
                        + oldPosition + " -> " + newPosition));
            }
        }
    }

    public void setMoves(MoveDirection[] moves){
        this.moves = moves;
    }

    public void addObserverToAnimals(IMotionObserver ob){
        for (Animal animal : animals){
            animal.addMotionObserver(ob);
        }
    }
    @Override
    public void run() {
        System.out.println(map);
        for (int i = 0; i < moves.length; i++){
            try {
                Thread.sleep(moveDelay);
                animals.get((i) % animals.size()).move(moves[i]);
                System.out.println(map);
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void setMoveDelay(int time) {
        this.moveDelay = time;
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
