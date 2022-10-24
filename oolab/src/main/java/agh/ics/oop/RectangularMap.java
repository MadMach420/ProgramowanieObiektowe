package agh.ics.oop;

import java.security.cert.TrustAnchor;
import java.util.*;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;
    public final List<Animal> animalList = new LinkedList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(width, height)) &&
                !isOccupied(position);
    }

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
        for (Animal animal : animalList) {
            if (animal.isAt(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
