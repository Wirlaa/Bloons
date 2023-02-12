package agh;

import java.util.ArrayList;

/**
 * Interface responsible for interacting with the map.
 * It places and stores map elements while checking if they stay in bounds.
 */
public interface IMap extends IPathObserver, IMapElementObserver {
    Balloon[] getBalloons();
    Tower[] getTowers();
    ArrayList<Path> getPaths();
    void moveBalloons();
    void placeBalloon(Balloon balloon);
    void placeBalloons(BalloonType type, int number);
    void placeTower(Tower tower);
    void addPath(Path path);
    boolean inBounds(Point point);
    void addObserver(IMapObserver observer);
    void removeObserver(IMapObserver observer);
}
