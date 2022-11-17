package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private IWorldMap map;
    private final SortedSet<Vector2d> elementsX = new TreeSet<>(
            (o1, o2) -> {
                if (o1.x - o2.x != 0) {
                    return o1.x - o2.x;
                } else if (o1.y - o2.y != 0) {
                    return o1.y - o2.y;
                } else if (map.objectAt(o1) instanceof Grass && map.objectAt(o2) instanceof Animal) {
                    return -1;
                } else  if (map.objectAt(o1) instanceof Animal && map.objectAt(o2) instanceof Grass){
                    return 1;
                } else {
                    return 0;
                }
            });
    private final SortedSet<Vector2d> elementsY = new TreeSet<>(
            (o1, o2) -> {
                if (o1.y - o2.y != 0) {
                    return o1.y - o2.y;
                } else if (o1.x - o2.x != 0) {
                    return o1.x - o2.x;
                } else if (map.objectAt(o1) instanceof Grass && map.objectAt(o2) instanceof Animal) {
                    return -1;
                } else  if (map.objectAt(o1) instanceof Animal && map.objectAt(o2) instanceof Grass){
                    return 1;
                } else {
                    return 0;
                }
            });

    public MapBoundary(IWorldMap map) {
        this.map = map;
    }

    public void addElement(Vector2d element) {
        elementsX.add(element);
        elementsY.add(element);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        elementsX.add(oldPosition);
        elementsY.add(oldPosition);
        elementsX.add(newPosition);
        elementsY.add(newPosition);
    }

    public Vector2d[] getBoundaries() {
        int minX = elementsX.first().x;
        int maxX = elementsX.last().x;
        int minY = elementsY.first().y;
        int maxY = elementsY.last().y;
        return new Vector2d[]{new Vector2d(minX, minY), new Vector2d(maxX, maxY)};
    }
}
