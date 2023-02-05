package agh;

import java.util.ArrayList;

/**
 * Class responsible for managing the shop.
 */
public class Shop {
    ArrayList<Tower> unlockedTowers = new ArrayList<>();

    public void unlockTower(Tower tower, int money) {
        if (tower.getUnlockPrice() <= money){
            unlockedTowers.add(tower);
        } //todo else jako warning jak bedziemy miec czas
    }

    public boolean canBuyTower(Tower tower, int money) {
        return unlockedTowers.contains(tower) && tower.getBuyingPrice() <= money;
        //todo else jako warning
    }
}
