package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public String toString() {
        return "Position: " + this.position + ", direction: " + direction;
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
                if (nextPosition.follows(new Vector2d(0, 0)) &&
                        nextPosition.precedes(new Vector2d(4, 4))) {
                    this.position = nextPosition;
                }
            }
            case BACKWARD -> {
                move = this.direction.next().next().toUnitVector();
                nextPosition = new Vector2d(
                        this.position.x + move.x,
                        this.position.y + move.y
                );
                if (nextPosition.follows(new Vector2d(0, 0)) &&
                        nextPosition.precedes(new Vector2d(4, 4))) {
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
}
