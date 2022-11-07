package agh.ics.oop;

import java.security.cert.TrustAnchor;
import java.util.*;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(width, height)) &&
                !isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public Vector2d[] getMapLimits() {
        return new Vector2d[]{new Vector2d(0, 0), new Vector2d(width, height)};
    }
}
