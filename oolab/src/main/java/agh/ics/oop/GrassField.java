package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    public final List<Grass> grassList = new LinkedList<>();

    public GrassField(int grassNumber) {
        for (int i = 0; i < grassNumber; i++) {
            Vector2d position = new Vector2d((int)(Math.random() * Math.sqrt(grassNumber)),
                    (int)(Math.random() * Math.sqrt(grassNumber)));

            boolean canPlace = true;
            for (Grass grass : grassList) {
                if (grass.getPosition().equals(position)) {
                    canPlace = false;
                    i--;
                    break;
                }
            }

            if (canPlace) {
                grassList.add(new Grass(position));
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        for (Grass grass: grassList) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public Vector2d[] getMapLimits() {
        int minX = animalList.get(0).getPosition().x, maxX = animalList.get(0).getPosition().x,
                minY = animalList.get(0).getPosition().y, maxY = animalList.get(0).getPosition().y;

        for (Animal animal : animalList) {
            minX = Math.min(minX, animal.getPosition().x);
            maxX = Math.max(maxX, animal.getPosition().x);
            minY = Math.min(minY, animal.getPosition().y);
            maxY = Math.max(maxY, animal.getPosition().y);
        }

        for (Grass grass : grassList) {
            minX = Math.min(minX, grass.getPosition().x);
            maxX = Math.max(maxX, grass.getPosition().x);
            minY = Math.min(minY, grass.getPosition().y);
            maxY = Math.max(maxY, grass.getPosition().y);
        }

        return new Vector2d[]{new Vector2d(minX - 1, minY - 1), new Vector2d(maxX + 1, maxY + 1)};
    }

    public static void main(String[] args) {
        IWorldMap map = new GrassField(10);
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println(map);
    }
}
