package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void testCanMoveTo() {
        RectangularMap map = new RectangularMap(5, 10);
        assert map.canMoveTo(new Vector2d(0,0));
        assert map.canMoveTo(new Vector2d(0,10));
        assert !map.canMoveTo(new Vector2d(-1,-1));
        new Animal(map, new Vector2d(0, 0));
        assert !map.canMoveTo(new Vector2d(0,0));
    }

    @Test
    public void testPlace() {
        RectangularMap map = new RectangularMap(5, 10);
        Animal testAnimal = new Animal();
        assert map.place(testAnimal);
        try {
            map.place(testAnimal);
        } catch (IllegalArgumentException e) {
            assert true; // Powinno tu wywalać błąd
        }
    }

    @Test
    public void testIsOccupied() {
        RectangularMap map = new RectangularMap(5, 10);
        assert !map.isOccupied(new Vector2d(0, 0));
        new Animal(map, new Vector2d(0, 0));
        assert map.isOccupied(new Vector2d(0, 0));
    }

    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(5, 10);
        assert map.objectAt(new Vector2d(0, 0)) == null;
        new Animal(map, new Vector2d(0, 0));
        assert map.objectAt(new Vector2d(0, 0)) instanceof Animal;
    }
}
