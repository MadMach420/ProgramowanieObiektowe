package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class Vector2dTest {
    @Test
    public void equals() {
        assert new Vector2d(1, 0).equals(new Vector2d(1, 0));
        assert !new Vector2d(0, 0).equals(new Vector2d(1, 1));
        assert new Vector2d(1, 2 ).equals(new Vector2d(2, 1));
//        assert !new Vector2d(0, 0).equals(new Integer[]{0, 0});
    }
}
