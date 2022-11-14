package agh.ics.oop;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private Set<AbstractWorldMapElement> elementsX = new TreeSet<AbstractWorldMapElement>(
            new Comparator<AbstractWorldMapElement>() {
                @Override
                public int compare(AbstractWorldMapElement o1, AbstractWorldMapElement o2) {
                    if (o1.getPosition().x - o2.getPosition().x != 0) {
                        return o1.getPosition().x - o2.getPosition().x;
                    } else if (o1.getPosition().y - o2.getPosition().y != 0) {
                            return o1.getPosition().y - o2.getPosition().y;
                    } else if (o1 instanceof Grass) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
