package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testSeriesOfCommands() {
        assert Animal.runSeriesOfCommands(new String[]{"f", "f", "r", "f"})
                .toString()
                .equals("Position: (3, 4), direction: Wschód");
        assert Animal.runSeriesOfCommands(new String[]{"f", "f", "f"})
                .toString()
                .equals("Position: (2, 4), direction: Północ");
        assert Animal.runSeriesOfCommands(new String[]{"b", "b", "r", "r", "f"})
                .toString()
                .equals("Position: (2, 0), direction: Południe");
        assert Animal.runSeriesOfCommands(new String[]{"invalid argument 1", "invalid argument 2"})
                .toString()
                .equals("Position: (2, 2), direction: Północ");
        assert Animal.runSeriesOfCommands(new String[]{"l", "invalid argument", "b"})
                .toString()
                .equals("Position: (3, 2), direction: Zachód");
        assert Animal.runSeriesOfCommands(new String[]{"l", "invalid argument", "f", "f", "f", "r", "b", "b", "r"})
                .toString()
                .equals("Position: (0, 0), direction: Wschód");
    }
}
