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
    public TowerType getType() {return type;}
    public int getRange() {return range;}
    public int getDamage() {return damage;}
    public int getFireRate() {return fireRate;}

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
    }

    @Override
    public void positionChanged(Point oldPosition, Point newPosition){
        for (IMapElementObserver observer: observers) {
            observer.positionChangedTower(oldPosition, newPosition, this);
        }
    }
}
