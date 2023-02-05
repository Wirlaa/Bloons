package agh;

/**
 * Interface responsible for managing the player's resources.
 */
public interface IPlayer {
    //ogolnie graczy moze byc wiecej, dzieki czemu mozna trzymac statystyki i pauzowac gry
    void unlockTower(Tower tower);
    void buyTower(Tower tower);
    void sellTower(Tower tower);
}
