package agh;

public interface IMapElementObserver {
    void positionChangedTower(Point oldPosition, Point newPosition, Tower tower);
    void positionChangedBalloon(Point oldPosition, Point newPosition, Balloon balloon);
}
