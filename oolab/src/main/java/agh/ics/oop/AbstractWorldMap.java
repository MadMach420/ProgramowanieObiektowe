package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    public final List<Animal> animalList = new LinkedList<>();

    @Override
    public boolean place(Animal animal) {
        if (animalList.contains(animal)) {
            return false;
        } else {
            animalList.add(animal);
            return true;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Vector2d[] getMapLimits() {

    }
}
