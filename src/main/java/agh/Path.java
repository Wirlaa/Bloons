package agh;

import java.util.ArrayList;
import java.util.List;

public class Path implements IPath {
    private Point[] pathPoints;
    private final List<IPathObserver> observers = new ArrayList<>();
    public Path(Point[] points) {this.pathPoints = points;}
    @Override
    public Point[] getPathPoints() {return pathPoints.clone();}
    @Override
    public Point getEntry() {return pathPoints[0];}
    @Override
    public Point getExit() {return pathPoints[pathPoints.length-1];}

    // mozna zrobic klase abstrakcyjną i rozszerzyć na różne typy ścieżek (linear, curved etc) ktore będą się różnić metodami

    @Override
    public double getY(Point position, int index, double step) {
        double a;
        if (pathPoints[index].x() - position.x() != 0) {
            a = (pathPoints[index].y() - position.y()) / (pathPoints[index].x() - position.x());
            return position.y() + a*(step);
        } else {
            return position.y() + step;
        }
    }

    @Override
    public boolean isVertical(Point position, int index) {
        return pathPoints[index].x() - position.x() == 0;
    }

    @Override
    public boolean isMovingRightUp(Point position, int index) {
        return position.x() < pathPoints[index].x() || (position.x() == pathPoints[index].x() && position.y() < pathPoints[index].y());
    }

    //ten sposob bedzie dzialac tylko na liniach, nie mam pojecia jak to zrobic na krzywych parametrycznych
    //prawdopodobnie sprawdzając parametr xD
    @Override
    public boolean isNewPositionBetweenPathPoints(Point position, Point newPosition, int pathIndex) {
        Point endPoint = pathPoints[pathIndex];
        double xDiff = endPoint.x() - position.x();
        double yDiff = endPoint.y() - position.y();

        if (Math.abs(xDiff) >= Math.abs(yDiff))
            return xDiff > 0 ?
                position.x() < newPosition.x() && newPosition.x() < endPoint.x() :
                endPoint.x() < newPosition.x() && newPosition.x() < position.x();
        else
            return yDiff > 0 ?
                position.y() < newPosition.y() && newPosition.y() < endPoint.y() :
                endPoint.y() < newPosition.y() && newPosition.y() < position.y();
    }

    @Override
    public void addObserver(IPathObserver observer) {observers.add(observer);}
    @Override
    public void removeObserver(IPathObserver observer) {observers.remove(observer);}
    @Override
    public void exitReached(Balloon balloon) {
        for (IPathObserver observer: observers) {
            observer.exitReached(balloon);
        }
    }

}
