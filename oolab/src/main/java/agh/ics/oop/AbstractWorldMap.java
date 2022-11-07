package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    public final Map<Vector2d, Animal> animalMap = new HashMap<>();

    @Override
    public boolean place(Animal animal) {
        return animalMap.put(animal.getPosition(), animal) == null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalMap.remove(oldPosition);
        animalMap.put(newPosition, animal);
    }

    public abstract Vector2d[] getMapLimits();

    @Override
    public String toString() {
        Vector2d[] limits = getMapLimits();
        return new MapVisualizer(this).draw(limits[0], limits[1]);
    }
}
