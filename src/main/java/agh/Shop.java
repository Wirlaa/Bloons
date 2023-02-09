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
        } //todo else jako warning jak bedziemy miec czas
    }

    public boolean canBuyTower(TowerType towerType, int money) {
        return unlockedTowerTypes.contains(towerType) && towerType.getBuyingPrice() <= money;
        //todo else jako warning
    }
}
