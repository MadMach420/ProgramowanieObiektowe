package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class GrassFieldTest {

    @Test
    public void testCanMoveTo() {
        GrassField map = new GrassField(10);
        assert map.canMoveTo(new Vector2d(0,0));
        assert map.canMoveTo(new Vector2d(-1,-1));
        map.place(new Animal(map, new Vector2d(0, 0)));
        assert !map.canMoveTo(new Vector2d(0,0));
    }

    @Test
    public void testPlace() {
        GrassField map = new GrassField(10);
        Animal testAnimal = new Animal();
        assert map.place(testAnimal);
        assert !map.place(testAnimal);
    }

    @Test
    public void testIsOccupied() {
        GrassField map = new GrassField(10);
        assert !map.isOccupied(new Vector2d(100, 100));
        map.place(new Animal(map, new Vector2d(100, 100)));
        assert map.isOccupied(new Vector2d(100, 100));
    }

    @Test
    public void testObjectAt() {
        GrassField map = new GrassField(10);
        assert map.objectAt(new Vector2d(100, 100)) == null;
        map.place(new Animal(map, new Vector2d(100, 100)));
        assert map.objectAt(new Vector2d(100, 100)) instanceof Animal;
    }
}
