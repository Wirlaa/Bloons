package agh;

/**
 * Interface responsible for managing balloons.
 */
public interface IBalloon {
    void move(double step);
    int getDropCount();
    int getSpawnCount();
    int getSpeed();
}
