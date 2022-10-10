package agh.ics.oop;

public enum Direction {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    public static Direction fromString(String arg) {
        return switch (arg) {
            case "f" -> FORWARD;
            case "b" -> BACKWARD;
            case "r" -> RIGHT;
            case "l" -> LEFT;
            default -> null;
        };
    }
}
