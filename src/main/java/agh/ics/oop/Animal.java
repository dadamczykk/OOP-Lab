package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private final IWorldMap map;
    private Vector2d position;
    private MapDirection orientation;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map){ // aby uprościć konstruktory w jednoparametrowym wywoływany jest dwuparametrowy
        this(map, new Vector2d(0, 0));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.orientation = MapDirection.NORTH;
//        if (!map.canMoveTo(initialPosition)) {
//            System.out.println("Pozycja " + initialPosition + " jest zajeta, albo znajduje sie poza granicami mapy.\n" +
//                    "Zwierze nie zostanie dodane do mapy.");
//        }
        this.position = initialPosition;

    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return switch(this.orientation){
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }


    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.orientation.toUniVector());
                if (map.canMoveTo(newPosition)){
                    positionChanged(this.position, newPosition);
                    this.position = newPosition;

                }

            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUniVector());
                if (map.canMoveTo(newPosition)){
                    positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
