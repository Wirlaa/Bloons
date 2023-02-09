package agh;

public class Balloon extends AMapElement implements IBalloon {
    private final BalloonType color;
    private Path path;
    private int pathIndex;
    private int dropCount;
    private int spawnCount;

    public Balloon(BalloonType color, Path path) {
        this.color = color;
        this.path = path;
        this.pathIndex = 1;
        this.position = path.getEntry();
        this.dropCount = color.getDropCount();
        this.spawnCount = color.getSpawnCount();
    }

    //trzeba testy napisac
    @Override
    public void move(double step) {
        Point newPosition = new Point(position.x() + step, path.getY(position, pathIndex, step));
        if (path.shouldIndexIncrement(position, newPosition, pathIndex)) {
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
