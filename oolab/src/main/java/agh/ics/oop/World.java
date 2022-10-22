package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println(Animal.runSeriesOfCommands(args));
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
