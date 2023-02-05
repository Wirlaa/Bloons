package agh;

import java.util.HashMap;

public class Player implements IPlayer {
    private final String name;
    private final Shop shop;
    private HashMap<Tower, Integer> towers = new HashMap<>();
    private int lifeCount;
    private int money;

    public Player(String name) {
        this.name = name;
        this.lifeCount = 3;
        this.money = 100;
        this.shop = new Shop();
    }

    public void unlockTower(Tower tower) {
        shop.unlockTower(tower, money);
    }

    public void buyTower(Tower tower) {
        if (shop.canBuyTower(tower, money)){
            int count = towers.getOrDefault(tower,0) + 1;
            towers.put(tower, count);
            money -= tower.getBuyingPrice();
        }
        // todo ewentualnie warning dac tutaj a nie w sklepie
    }

    public void sellTower(Tower tower) {
        if (towers.containsKey(tower)){
            int count = towers.get(tower) - 1;
            if (count != 0) {
                towers.put(tower, count);
            } else {
                towers.remove(tower);
            }
            money += tower.getSellingPrice();
        }
        // todo ewentualnie warning dac tutaj a nie w sklepie
    }

}
