package agh;

/**
 * Interface responsible for managing towers.
 */
public interface ITower {
    void upgrade();
    TowerType getType();
    int getRange();
    int getDamage();
    int getFireRate();
    void changePosition(Point newPosition);
}
