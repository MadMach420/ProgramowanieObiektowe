package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        try {
            IWorldMap map = new GrassField(10);
            MoveDirection[] directions = OptionsParser.parse(args);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void run(Direction[] args) {
        for (Direction arg : args) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case LEFT -> System.out.println("Zwierzak skręca w prawo");
                case RIGHT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }

    public static Direction[] prepareArrayOfDirections(String[] args) {
        Direction[] arrToReturn = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            arrToReturn[i] = Direction.fromString(args[i]);
        }
        return arrToReturn;
    }
}
