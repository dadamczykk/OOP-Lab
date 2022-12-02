package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private final int grassNumber;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    public GrassField(int grassNumber) {
        this.grassNumber = grassNumber;
        while (grassNumber > 0) {
            Vector2d toPlace = new Vector2d((int) (Math.random() * Math.sqrt(10 * this.grassNumber)),
                    (int) (Math.random() * Math.sqrt(10 * this.grassNumber)));
            if (placeGrass(new Grass(toPlace))) {
                boundary.addVec(toPlace);
                grassNumber -= 1;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (isOccupied(position)) {
            Object o = objectAt(position);
            if (o instanceof Animal){
                return false;
            }
            if (o instanceof Grass){ // ten fragment odpowiada za działanie zadania 'dla zaawansowanych'
                Vector2d toPlace = new Vector2d((int) (Math.random() * Math.sqrt(10 * this.grassNumber)),
                        (int) (Math.random() * Math.sqrt(10 * this.grassNumber)));
                while (!placeGrass(new Grass(toPlace))){
                    toPlace = new Vector2d((int) (Math.random() * Math.sqrt(10 * this.grassNumber)),
                            (int) (Math.random() * Math.sqrt(10 * this.grassNumber)));
                }
                boundary.positionChanged(((Grass) o).getPosition(), toPlace);
                grasses.remove(((Grass) o).getPosition());
            }
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        if (grasses.containsKey(position)){
            return grasses.get(position);
        }
        return null;
    }

    @Override
    public Vector2d[] getBoundingVectors() {
        return boundary.getBoundingVectors();
    }


    public boolean placeGrass(Grass grass) {
        if (isOccupied(grass.getPosition())) {
            return false;
        } else {
            this.grasses.put(grass.getPosition(), grass);
            return true;
        }
    }

    public boolean hasGrassOnPosition(Vector2d position) {
        return grasses.containsKey(position);
    }
}