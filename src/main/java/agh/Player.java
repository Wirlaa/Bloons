package agh;

import java.util.HashMap;

public class Player implements IPlayer {
    private final String name;
    private final Shop shop;
    private HashMap<TowerType, Integer> towers = new HashMap<>();//todo trzeba zmienic
    private int lifeCount;
    private int money;

    public Player(String name) {
        this.name = name;
        this.lifeCount = 3;
        this.money = 100;
        this.shop = new Shop();
    }

    public void decrementLife(){
        lifeCount--;
        //todo info dla gracza ze stracil zycie
    }

    public void unlockTower(TowerType towerType) {
        shop.unlockTower(towerType, money);
    }

    public void buyTower(TowerType towerType) {
        if (shop.canBuyTower(towerType, money)){
            int count = towers.getOrDefault(towerType,0) + 1;
            towers.put(towerType, count);
            money -= towerType.getBuyingPrice();
        }
        // todo ewentualnie warning dac tutaj a nie w sklepie
    }

    public void sellTower(TowerType towerType) {
        if (towers.containsKey(towerType)){
            int count = towers.get(towerType) - 1;
            if (count != 0) {
                towers.put(towerType, count);
            } else {
                towers.remove(towerType);
            }
            money += towerType.getSellingPrice();
        }
        // todo ewentualnie warning dac tutaj a nie w sklepie
    }

}
