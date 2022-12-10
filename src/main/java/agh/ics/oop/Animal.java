package agh.ics.oop;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement, IMotionObserver, IPositionChangeObserver{
    private final IWorldMap map;
    private Vector2d position;
    private MapDirection orientation;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private final List<IMotionObserver> motionObservers = new ArrayList<>();

    public Animal(IWorldMap map){
        this(map, new Vector2d(0, 0));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.orientation = MapDirection.NORTH;
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

    @Override
    public InputStream getImgSrc() {
        return getClass().getResourceAsStream(switch(this.getOrientation()){
            case NORTH -> "catUp.png";
            case SOUTH -> "catDown.png";
            case EAST -> "catRight.png";
            case WEST -> "catLeft.png";
        });
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
        this.motionDetected();
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
    public void addMotionObserver(IMotionObserver ob) {
        motionObservers.add(ob);
    }
    public void removeMotionObserver(IMotionObserver ob){
        motionObservers.remove(ob);
    }

    @Override
    public void motionDetected() {
        for (IMotionObserver ob : motionObservers){
            ob.motionDetected();
        }
    }
}
