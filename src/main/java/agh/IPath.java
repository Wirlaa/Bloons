package agh;

/**
 * Interface responsible for managing paths.
 */
public interface IPath {
    Point[] getPathPoints();
    Point getEntry();
    Point getExit();
    boolean isVertical(Point position, int index);
    boolean isMovingRightUp(Point position, int index);
    double getY(Point position, int index, double step);
    boolean isNewPositionBetweenPathPoints(Point position, Point newPosition, int pathIndex);
    void addObserver(IPathObserver observer);
    void removeObserver(IPathObserver observer);
    void exitReached(Balloon balloon);
}
