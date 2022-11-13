package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private final List<Animal> animals;
    private final MapVisualizer mapVisualizer;
    private final static Vector2d bottomLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizerSwing mapVisualizerSwing;

    public RectangularMap(int width, int height){
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.animals = new ArrayList<>();
        this.mapVisualizer = new MapVisualizer(this);
        this.mapVisualizerSwing = new MapVisualizerSwing(this, this.upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bottomLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

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
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        mapVisualizerSwing.addScene(this);
        return this.mapVisualizer.draw(bottomLeft, upperRight);
    }

    public void run(){
        mapVisualizerSwing.run();
    }
}
