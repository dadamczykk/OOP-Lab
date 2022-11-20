package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected final List<Animal> animals = new ArrayList<>();
    protected final List<Grass> grasses = new ArrayList<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    public abstract Vector2d[] getBoundingVectors();

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if (animal.isAt(position)){
                return true;
            }
        }
        for (Grass grass : grasses){
            if (grass.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.isAt(position)){
                return animal;
            }
        }
        for (Grass grass : grasses){
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        Vector2d[] bounds = getBoundingVectors();
        return this.mapVisualizer.draw(bounds[0], bounds[1]);
    }
}
