package agh;

import java.util.ArrayList;

/**
 * Interface responsible for interacting with the map.
 */
public interface IMap {
    Balloon[] getBalloons();
    ArrayList<Path> getPaths();
    void moveBalloons();
    void placeBalloon(Balloon balloon);
    void placeTower(Tower tower); //czy potrzebne tutaj? plus pozostale funkcje tower
    void addPath(Path path);
    void addObserver(IMapObserver observer);
    void removeObserver(IMapObserver observer);
}
