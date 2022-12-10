package agh.ics.oop;

import java.io.InputStream;

public class Grass implements IMapElement {
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

    @Override
    public InputStream getImgSrc() throws NullPointerException {
        return getClass().getResourceAsStream("grass.png");
    }
}
