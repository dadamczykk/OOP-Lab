package agh.ics.oop;

public class Grass implements IMapElement { //abstractWorldMapElement miałoby sens, jakby Grass i Animal miały więcej elementów wspólnych.
    private final Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
