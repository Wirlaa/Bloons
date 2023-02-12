package agh;

import java.util.ArrayList;

/**
 * Class responsible for managing the shop.
 */
public class Shop {
    private final ArrayList<TowerType> unlockedTowerTypes = new ArrayList<>();

    public void unlockTower(TowerType towerType, int money) {
        if (towerType.getUnlockPrice() <= money){
            unlockedTowerTypes.add(towerType);
        } //todo warning jak za malo money
    }

    public boolean canBuyTower(TowerType towerType, int money) {
        return unlockedTowerTypes.contains(towerType) && towerType.getBuyingPrice() <= money;
    }
}
