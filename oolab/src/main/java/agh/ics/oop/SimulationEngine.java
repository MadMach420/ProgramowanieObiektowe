package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        int animalToMove = 0;
        List<Animal> animalList = ((RectangularMap)map).animalList;
        for (MoveDirection direction : directions) {
            animalList.get(animalToMove).move(direction);
            animalToMove = (animalToMove + 1) % animalList.size();
        }
    }
}
