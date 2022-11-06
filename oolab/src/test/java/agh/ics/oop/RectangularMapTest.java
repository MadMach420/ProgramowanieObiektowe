package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void testCanMoveTo() {
        RectangularMap map = new RectangularMap(5, 10);
        assert map.canMoveTo(new Vector2d(0,0));
        assert map.canMoveTo(new Vector2d(0,10));
        assert !map.canMoveTo(new Vector2d(-1,-1));
        map.place(new Animal(map, new Vector2d(0, 0)));
        assert !map.canMoveTo(new Vector2d(0,0));
    }

    @Test
    public void testPlace() {
        RectangularMap map = new RectangularMap(5, 10);
        Animal testAnimal = new Animal();
        assert map.place(testAnimal);
        assert !map.place(testAnimal);
    }

    @Test
    public void testIsOccupied() {
        RectangularMap map = new RectangularMap(5, 10);
        assert !map.isOccupied(new Vector2d(0, 0));
        map.place(new Animal(map, new Vector2d(0, 0)));
        assert map.isOccupied(new Vector2d(0, 0));
    }

    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(5, 10);
        assert map.objectAt(new Vector2d(0, 0)) == null;
        map.place(new Animal(map, new Vector2d(0, 0)));
        assert map.objectAt(new Vector2d(0, 0)) instanceof Animal;
    }
}
