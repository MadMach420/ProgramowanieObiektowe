package agh.ics.oop;

import java.util.*;

public class SimulationEngine implements IEngine, Runnable{
    private final IWorldMap map;
    private MoveDirection[] directions;
    private final List<ISimulationChangeObserver> observerList = new LinkedList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions) {
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
        }
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    @Override
    public void run() {
        simulationStepAlert();
        int animalToMove = 0;
        List<Animal> animalList = new ArrayList<>(((AbstractWorldMap)map).animalMap.values());
        for (MoveDirection direction : directions) {
            animalList.get(animalToMove).move(direction);
            animalToMove = (animalToMove + 1) % animalList.size();
            simulationStepAlert();
        }
        simulationStepAlert();
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
