package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

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

    public static Direction changeArgToEnum(String arg) {
        return switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> null;
        };
    }
}
