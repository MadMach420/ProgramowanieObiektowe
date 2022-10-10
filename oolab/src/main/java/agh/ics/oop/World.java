package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
//        Direction[] argsToRun = prepareArrayOfDirections(args); // robi to samo co one liner poniżej
        Direction[] argsToRun = Arrays.stream(args)
                .map(Direction::fromString)
                .filter(Objects::nonNull)
                .toArray(Direction[]::new);
        run(argsToRun);
        System.out.println("Stop");
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
