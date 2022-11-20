package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private final static Vector2d bottomLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizerSwing mapVisualizerSwing;

    public RectangularMap(int width, int height){
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.mapVisualizerSwing = new MapVisualizerSwing(this, this.upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bottomLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public Vector2d[] getBoundingVectors() {
        return new Vector2d[] {bottomLeft, upperRight};
    }

//    @Override
//    public String toString(){
//        mapVisualizerSwing.addScene(this);
//        return this.mapVisualizer.draw(bottomLeft, upperRight);
//    }

    public void run(){
        mapVisualizerSwing.run();
    }
}
