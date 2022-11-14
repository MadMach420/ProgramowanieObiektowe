package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class GrassFieldTest {

    @Test
    public void testCanMoveTo() {
        GrassField map = new GrassField(10);
        assert map.canMoveTo(new Vector2d(0,0));
        assert map.canMoveTo(new Vector2d(-1,-1));
        new Animal(map, new Vector2d(0, 0));
        assert !map.canMoveTo(new Vector2d(0,0));
    }

    @Test
    public void testPlace() {
        GrassField map = new GrassField(10);
        Animal testAnimal = new Animal();
        assert map.place(testAnimal);
        try {
            map.place(testAnimal);
        } catch (IllegalArgumentException e) {
            assert true; // Powinno wywalać tu błąd
        }
    }

    @Test
    public void testIsOccupied() {
        GrassField map = new GrassField(10);
        assert !map.isOccupied(new Vector2d(100, 100));
        new Animal(map, new Vector2d(100, 100));
        assert map.isOccupied(new Vector2d(100, 100));
    }

    @Test
    public void testObjectAt() {
        GrassField map = new GrassField(10);
        assert map.objectAt(new Vector2d(100, 100)) == null;
        new Animal(map, new Vector2d(100, 100));
        assert map.objectAt(new Vector2d(100, 100)) instanceof Animal;
    }
}
