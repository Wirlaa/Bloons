package agh;

/**
 * Interface responsible for managing the player's resources.
 */
public interface IPlayer {
    int getLifeCount();
    int getMoney();
    String getName();
    void decrementLife();
    boolean isUnlocked(TowerType towerType);
    boolean unlockTower(TowerType towerType);
    boolean canBuyTower(TowerType towerType);
    void buyTower(TowerType towerType);
    void sellTower(TowerType towerType);
    void addMoney(int income);
}
