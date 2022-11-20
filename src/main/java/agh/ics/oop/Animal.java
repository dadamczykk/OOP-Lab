package agh.ics.oop;

public class Animal implements IMapElement{
    private final IWorldMap map;
    private Vector2d position;
    private MapDirection orientation;
    //konstruktor bezparametrowy nie ma sensu, do działania metod klasy konieczne jest zdefiniowanie mapy.
    public Animal(IWorldMap map){ // aby uprościć konstruktory w jednoparametrowym wywoływany jest dwuparametrowy
        this(map, new Vector2d(0, 0));
    }
    // Aby uprościć konstruktory, w jednoparametrowym konstruktorze umieszczam wywołanie dwuparametrowego.
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.orientation = MapDirection.NORTH;
        if (!map.canMoveTo(initialPosition)) {
            System.out.println("Pozycja " + initialPosition + " jest zajeta, albo znajduje sie poza granicami mapy.\n" +
                    "Zwierze nie zostanie dodane do mapy.");
        }
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
                    this.position = newPosition;
                }

            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUniVector());
                if (map.canMoveTo(newPosition)){
                    this.position = newPosition;
                }
            }
        }
    }

}
