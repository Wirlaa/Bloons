package agh;


import static agh.TowerType.*;

public class Tower extends AMapElement implements ITower {
    private TowerType type;
    private int range;
    private int damage;
    private int fireRate;

    public Tower(TowerType type, Point position) {
        this.type = type;
        this.position = position;
        this.range = type.getStartingRange();
        this.damage = type.getStartingDamage();
        this.fireRate = type.getStartingFireRate();
    }
    @Override
    public TowerType getType() {return type;}
    @Override
    public int getRange() {return range;}
    @Override
    public int getDamage() {return damage;}
    @Override
    public int getFireRate() {return fireRate;}

    @Override
    public void changePosition(Point newPosition) {
        positionChanged(position, newPosition);
        position = newPosition;
    }

    @Override
    public void upgrade() {
        type = switch(type){
            case BASIC -> MEDIUM;
            case MEDIUM, ADVANCED -> ADVANCED;
            //todo error message ze nie da sie upgradowac advanced
        };
        this.range = type.getStartingRange();
        this.damage = type.getStartingDamage();
        this.fireRate = type.getStartingFireRate();
    }

    @Override
    public void positionChanged(Point oldPosition, Point newPosition){
        for (IMapElementObserver observer: observers) {
            observer.positionChangedTower(oldPosition, newPosition, this);
        }
    }
}
