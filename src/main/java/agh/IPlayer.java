package agh;

/**
 * Interface responsible for managing the player's resources.
 */
public interface IPlayer {
    //ogolnie graczy moze byc wiecej, dzieki czemu moze bedzie mozna trzymac statystyki i pauzowac gry
    int getLifeCount();
    void decrementLife();
    void unlockTower(TowerType towerType);
    void buyTower(TowerType towerType);
    void sellTower(TowerType towerType);
    void addMoney(int income);
}
