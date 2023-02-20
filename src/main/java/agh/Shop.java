package agh;

import java.util.ArrayList;

/**
 * Class responsible for managing the shop.
 */
public class Shop {
    private final ArrayList<TowerType> unlockedTowerTypes = new ArrayList<>();

    public boolean unlockTower(TowerType towerType, int money) {
        if (towerType.getUnlockPrice() <= money){
            unlockedTowerTypes.add(towerType);
            return true;
        }
        return false;
    }

    public boolean canBuyTower(TowerType towerType, int money) {
        return unlockedTowerTypes.contains(towerType) && towerType.getBuyingPrice() <= money;
    }

    public boolean isUnlocked(TowerType towerType) {return unlockedTowerTypes.contains(towerType);}
}
