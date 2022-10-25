package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class SimulationEngineTest {
    @Test
    public void testRun() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        MoveDirection[] testDirections =
                {MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT};
        IWorldMap testMap = new RectangularMap(10, 5);
        IEngine testEngine = new SimulationEngine(testDirections, testMap,
                new Vector2d[]{new Vector2d(2, 0), new Vector2d(3, 5)});
        testEngine.run();
        assert map.toString().equals(testMap.toString());
    }
}
