package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        return(Arrays.stream(args)
                .map(MoveDirection::fromString)  // Nowa metoda zaimplementowana w MoveDirection
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new)
        );
    }
}
