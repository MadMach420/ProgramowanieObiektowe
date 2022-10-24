package agh.ics.oop;

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
        for (MoveDirection direction : directions) {
            map.animalList
        }
    }
}
