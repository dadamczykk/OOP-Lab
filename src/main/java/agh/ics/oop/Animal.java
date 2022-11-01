package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    private final Vector2d upperBoundary = new Vector2d(4, 4);

    private final Vector2d bottomBoundary = new Vector2d(0, 0);

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "pozycja: " + this.position + ", orientacja: " + this.orientation;
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
                if (newPosition.precedes(upperBoundary)  && newPosition.follows(bottomBoundary)){
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUniVector());
                if (newPosition.precedes(upperBoundary)  && newPosition.follows(bottomBoundary)){
                    this.position = newPosition;
                }
            }
        }
    }

}
