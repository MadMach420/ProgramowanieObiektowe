package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class Animal implements IMapElement {
    private IWorldMap map;
    private MapDirection direction = MapDirection.NORTH;
    protected Vector2d position = new Vector2d(2, 2);
    private final List<IPositionChangeObserver> observerList = new LinkedList<>();

    public Animal(IWorldMap map) {
        this.map = map;
        addObserver((AbstractWorldMap)map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        addObserver((AbstractWorldMap)map);
        this.position = initialPosition;
        map.place(this);
    }

    public Animal() {}

    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void move(MoveDirection direction) {
        Vector2d move, nextPosition;
        switch (direction) {
            case FORWARD -> {
                move = this.direction.toUnitVector();
                nextPosition = new Vector2d(
                        this.position.x + move.x,
                        this.position.y + move.y
                );
                if (map.canMoveTo(nextPosition)) {
                    if (observerList.size() > 0) {
                        positionChanged(this.position, nextPosition);
                    }
                    this.position = nextPosition;
                }
            }
            case BACKWARD -> {
                move = this.direction.next().next().toUnitVector();
                nextPosition = new Vector2d(
                        this.position.x + move.x,
                        this.position.y + move.y
                );
                if (map.canMoveTo(nextPosition)) {
                    if (observerList.size() > 0) {
                        positionChanged(this.position, nextPosition);
                    }
                    this.position = nextPosition;
                }
            }
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
        }
    }

    /** Metoda do wywołania w World.main() i w testach.
     * Wolę zdefiniować w klasie Animal, aby testy się nie wysypały w przypadku dalszych modyfikacji World.main()
     */
    public static Animal runSeriesOfCommands(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);
        Animal zwierzak = new Animal();
        for (MoveDirection direction : directions) {
            zwierzak.move(direction);
        }
        return(zwierzak);
    }

    public Vector2d getPosition() {
        return position;
    }

    public String getImageUrl() {
        return switch (this.direction) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
            case EAST -> "src/main/resources/right.png";
        };
    }

    public void addObserver(IPositionChangeObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observerList.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
