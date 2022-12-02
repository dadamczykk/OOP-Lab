package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractWorldMap implements IWorldMap{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected final MapBoundary boundary = new MapBoundary();

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    public abstract Vector2d[] getBoundingVectors();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal toMove = animals.remove(oldPosition);
        animals.put(newPosition, toMove);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            animal.addObserver(boundary);
            boundary.addVec(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition() + " jest niepoprawną pozycją");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }


    @Override
    public String toString(){
        Vector2d[] bounds = getBoundingVectors();
        return this.mapVisualizer.draw(bounds[0], bounds[1]);
    }
}
