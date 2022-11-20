package agh.ics.oop;

public class GrassField extends AbstractWorldMap {
    private final int grassNumber;
    public GrassField(int grassNumber) {
        this.grassNumber = grassNumber;
        while (grassNumber > 0) {
            Vector2d toPlace = new Vector2d((int) (Math.random() * Math.sqrt(10 * this.grassNumber)),
                    (int) (Math.random() * Math.sqrt(10 * this.grassNumber)));
            if (placeGrass(new Grass(toPlace))) {
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
                grasses.remove(o);
            }
        }
        return true;
    }

    @Override
    public Vector2d[] getBoundingVectors() {
        Vector2d bottomLeft = animals.get(0).getPosition();
        Vector2d upperRight = animals.get(0).getPosition();
        for (Animal animal : animals) {
            upperRight = animal.getPosition().upperRight(upperRight);
            bottomLeft = animal.getPosition().lowerLeft(bottomLeft);
        }
        for (Grass grass : grasses) {
            upperRight = grass.getPosition().upperRight(upperRight);
            bottomLeft = grass.getPosition().lowerLeft(bottomLeft);
        }
        return new Vector2d[] {bottomLeft, upperRight};
    }


    public boolean placeGrass(Grass grass) {
        if (isOccupied(grass.getPosition())) {
            return false;
        } else {
            this.grasses.add(grass);
            return true;
        }
    }

    public boolean hasGrassOnPosition(Vector2d position) {
        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
}