package agh;

public interface IMapObserver extends IPathObserver {
    void balloonPopped(Balloon balloon);
    void buyTower(Tower tower);
    void sellTower(Tower tower);
    void unlockTower(Tower tower);
}
