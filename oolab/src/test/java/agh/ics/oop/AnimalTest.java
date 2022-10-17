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
    }
}
