package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    public final Map<Vector2d, Grass> grassMap = new HashMap<>();
    private final MapBoundary boundary = new MapBoundary(this);

    public GrassField(int grassNumber) {
        for (int i = 0; i < grassNumber; i++) {
            Vector2d position = new Vector2d((int)(Math.random() * Math.sqrt(grassNumber)),
                    (int)(Math.random() * Math.sqrt(grassNumber)));

            Grass grass = new Grass(position);
            if (grassMap.put(position, grass) == null) {
                boundary.addElement(grass.getPosition());
            } else {
                i--;
            }
        }
    }

    @Override
    public boolean place(Animal animal) {
        super.place(animal);
        boundary.addElement(animal.getPosition());
        animal.addObserver(boundary);
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animalMap.get(position) != null) {
            return animalMap.get(position);
        }
        return grassMap.get(position);
    }

    @Override
    public Vector2d[] getMapLimits() {
        return boundary.getBoundaries();
    }
}
