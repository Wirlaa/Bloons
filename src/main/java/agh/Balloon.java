package agh;

public class Balloon implements IBalloon {
    private final BalloonType color;
    private int dropCount;
    private int spawnCount;
    //path?

    public Balloon(BalloonType color) {
        this.color = color;
        this.dropCount = color.getDropCount();
        this.spawnCount = color.getSpawnCount();
    }

    @Override
    public void move() {
        //todo
    }
}
