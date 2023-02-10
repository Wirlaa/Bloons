package agh;

public class Player implements IPlayer {
    private final String name;
    private final Shop shop;
    private int lifeCount;
    private int money;

    public Player(String name) {
        this.name = name;
        this.lifeCount = 3;
        this.money = 100;
        this.shop = new Shop();
    }

    @Override
    public int getLifeCount() { return lifeCount;}

    @Override
    public void decrementLife(){
        lifeCount--;
        //todo info dla gracza ze stracil zycie
    }

    @Override
    public void unlockTower(TowerType towerType) {
        shop.unlockTower(towerType, money);
    }

    @Override
    public void buyTower(TowerType towerType) {
        if (shop.canBuyTower(towerType, money)){
            money -= towerType.getBuyingPrice();
        }
        //todo ewentualnie warning dac tutaj a nie w sklepie
    }

    @Override
    public void sellTower(TowerType towerType) {
        money += towerType.getSellingPrice();
        //todo ewentualnie warning dac tutaj a nie w sklepie
    }

    @Override
    public void addMoney(int income) {
        if (income > 0) {
            money += income;
        } //todo warning
    }

}
