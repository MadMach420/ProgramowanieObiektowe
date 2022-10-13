package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test
    public void next(){
        for (MapDirection direction : MapDirection.values()) {
            switch (direction) {
                case NORTH:
                    assert MapDirection.NORTH.next() == MapDirection.EAST;
                case EAST:
                    assert MapDirection.EAST.next() == MapDirection.SOUTH;
                case SOUTH:
                    assert MapDirection.SOUTH.next() == MapDirection.WEST;
                case WEST:
                    assert MapDirection.WEST.next() == MapDirection.NORTH;
            }
        }
    }

    @Test
    public void previous(){
        for (MapDirection direction : MapDirection.values()) {
            switch (direction) {
                case NORTH:
                    assert MapDirection.NORTH.previous() == MapDirection.WEST;
                case EAST:
                    assert MapDirection.EAST.previous() == MapDirection.NORTH;
                case SOUTH:
                    assert MapDirection.SOUTH.previous() == MapDirection.EAST;
                case WEST:
                    assert MapDirection.WEST.previous() == MapDirection.SOUTH;
            }
        }
    }
}
