package agh;

/**
 * Interface responsible for managing the player's resources.
 */
public interface IPlayer {
    //ogolnie graczy moze byc wiecej, dzieki czemu mozna trzymac statystyki i pauzowac gry
    void unlockTower(TowerType towerType);
    void buyTower(TowerType towerType);
    void sellTower(TowerType towerType);
}
