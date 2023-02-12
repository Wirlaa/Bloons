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

    public Player(String name, int lifeCount) {
        this(name);
        this.lifeCount = lifeCount;
    }

    @Override
    public int getLifeCount() { return lifeCount;}
    @Override
    public int getMoney() {return money;}
    @Override
    public String getName() {return name;}

    @Override
    public void decrementLife(){
        lifeCount--;
        //todo info dla gracza ze stracil zycie
    }

    @Override
    public void unlockTower(TowerType towerType) {shop.unlockTower(towerType, money);}

    @Override
    public boolean canBuyTower(TowerType towerType) {return shop.canBuyTower(towerType, money);}

    @Override
    public void buyTower(TowerType towerType) {money -= towerType.getBuyingPrice();}

    @Override
    public void sellTower(TowerType towerType) {money += towerType.getSellingPrice();}

    @Override
    public void addMoney(int income) {
        if (income > 0) {
            money += income;
        } //todo warning
    }

}
