package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] directions;
    private final List<ISimulationChangeObserver> observerList = new LinkedList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
        }
    }

    @Override
    public void run() {
        int animalToMove = 0;
        List<Animal> animalList = new ArrayList<>(((AbstractWorldMap)map).animalMap.values());
        for (MoveDirection direction : directions) {
            animalList.get(animalToMove).move(direction);
            animalToMove = (animalToMove + 1) % animalList.size();
            simulationStepAlert();
        }
    }

    public void addObserver(ISimulationChangeObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(ISimulationChangeObserver observer) {
        observerList.remove(observer);
    }

    public void simulationStepAlert() {
        for (ISimulationChangeObserver observer : observerList) {
            observer.simulationStep();
        }
    }
}
