package agh.ics.oop;


import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    // Wystarczy trzymać wektory - jedynie one są potrzebne do określenia granic mapy.
    // Jeżeli w SortedSecie przetrzymywane były obiekty klasy Animal, to możliwe byłyby błędy,
    // gdzie została zmieniona pozycja zwierzęcia, ale nie zostało to zaktualizowane w posortowanym zbiorze

    private static final Comparator<Vector2d> xCompare = Comparator
            .<Vector2d>comparingInt(x -> x.x).thenComparingInt(x -> x.y);
    private static final Comparator<Vector2d> yCompare = Comparator
            .<Vector2d>comparingInt(x -> x.y).thenComparingInt(x -> x.x);

    private final TreeSet<Vector2d> xSorted = new TreeSet<>(xCompare);
    private final TreeSet<Vector2d> ySorted = new TreeSet<>(yCompare);

    public void addVec(Vector2d vec){
        xSorted.add(vec);
        ySorted.add(vec);
    }

    public void removeVec(Vector2d vec){
        xSorted.remove(vec);
        ySorted.remove(vec);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeVec(oldPosition);
        addVec(newPosition);
    }

    public Vector2d[] getBoundingVectors() {
        Vector2d bottomLeft = new Vector2d(this.xSorted.first().x, this.ySorted.first().y);
        Vector2d upperRight = new Vector2d(this.xSorted.last().x, this.ySorted.last().y);
        return new Vector2d[]{bottomLeft, upperRight};
    }
}
