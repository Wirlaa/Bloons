package agh;

public class Balloon extends AMapElement implements IBalloon {
    private final BalloonType color;
    private IPath path;
    private int pathIndex;
    private int dropCount;
    private int spawnCount;
    private int speed;

    public Balloon(Balloon balloon) {
        // nie ma checku jakby balon byl czerwony, bo wtedy kolor bedzie nullem
        // czy powinien check byc w inicie?
        this(balloon.color.getNextColor(), balloon.path);
        this.position = balloon.position;
    }

    public Balloon(BalloonType color, IPath path) {
        this.color = color;
        this.path = path;
        this.pathIndex = 1;
        this.position = path.getEntry();
        this.dropCount = color.getMoneyDrop();
        this.spawnCount = color.getSpawnCount();
        this.speed = color.getStartingSpeed();
    }

    @Override
    public int getDropCount() {return dropCount;}
    @Override
    public int getSpawnCount() {return spawnCount;}
    @Override
    public boolean isLastColor() {return color.getNextColor() == null;}


    @Override
    public void move() {
        int step = speed;
        if (!path.isMovingRightUp(position, pathIndex)) step *= -1;
        double newX = position.x();
        if (!path.isVertical(position, pathIndex)) newX = position.x() + step;
        Point newPosition = new Point(newX, path.getY(position, pathIndex, step));
        if (!path.isNewPositionBetweenPathPoints(position, newPosition, pathIndex)) {
            newPosition = path.getPathPoints()[pathIndex];
            pathIndex++;
        }
        positionChanged(position, newPosition);
        position = newPosition;
        if (position == path.getExit()) {
            path.exitReached(this);
        }
    }

    @Override
    public void positionChanged(Point oldPosition, Point newPosition){
        for (IMapElementObserver observer: observers) {
            observer.positionChangedBalloon(oldPosition, newPosition, this);
        }
    }

}
