package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    public final Map<Vector2d, Grass> grassMap = new HashMap<>();

    public GrassField(int grassNumber) {
        for (int i = 0; i < grassNumber; i++) {
            Vector2d position = new Vector2d((int)(Math.random() * Math.sqrt(grassNumber)),
                    (int)(Math.random() * Math.sqrt(grassNumber)));

            if (grassMap.put(position, new Grass(position)) != null) {
                i--;
            }
        }
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
        int minX = 1000000, maxX = -1000000, minY = 1000000, maxY = -1000000;

        for (Animal animal : animalMap.values()) {
            minX = Math.min(minX, animal.getPosition().x);
            maxX = Math.max(maxX, animal.getPosition().x);
            minY = Math.min(minY, animal.getPosition().y);
            maxY = Math.max(maxY, animal.getPosition().y);
        }

        for (Grass grass : grassMap.values()) {
            minX = Math.min(minX, grass.getPosition().x);
            maxX = Math.max(maxX, grass.getPosition().x);
            minY = Math.min(minY, grass.getPosition().y);
            maxY = Math.max(maxY, grass.getPosition().y);
        }

        return new Vector2d[]{new Vector2d(minX - 1, minY - 1), new Vector2d(maxX + 1, maxY + 1)};
    }
}
