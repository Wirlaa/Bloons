package agh;

import java.util.ArrayList;
import java.util.List;

public class Path implements IPath {
    private Point[] pathPoints;
    protected final List<IPathObserver> observers = new ArrayList<>();
    public Path(Point[] points) {this.pathPoints = points;}
    public Point[] getPathPoints() {return pathPoints.clone();}
    public Point getEntry() {return pathPoints[0];}
    public Point getExit() {return pathPoints[pathPoints.length-1];}

    // mozna zrobic klase abstrakcyjną i dodawać różne typy ścieżek (linear, curved etc) ktore będą się różnić tą funkcją
    // trzeba napisac testy xD
    public double getY(Point position, int index, double step) {
        double a = pathPoints[index].y() - position.y();
        double b = position.x() - pathPoints[index].x();
        double c = a*position.x() + b*position.y();
        if (b != 0) {
            return (c - a*(position.x() + step)) / b;
        } else return position.y();
    }

    //dziala tylko na liniach, nie mam pojecia jak to zrobic na krzywych
    public boolean shouldIndexIncrement(Point position, Point newPosition, int pathIndex) {
        Point endPoint = pathPoints[pathIndex];
        double xDiff = endPoint.x() - position.x();
        double yDiff = endPoint.y() - position.y();

        if (Math.abs(xDiff) >= Math.abs(yDiff))
            return xDiff > 0 ?
                    position.x() <= newPosition.x() && newPosition.x() <= endPoint.x() :
                    endPoint.x() <= newPosition.x() && newPosition.x() <= position.x();
        else
            return yDiff > 0 ?
                    position.y() <= newPosition.y() && newPosition.y() <= endPoint.y() :
                    endPoint.y() <= newPosition.y() && newPosition.y() <= position.y();
    }

    public void addObserver(IPathObserver observer){ observers.add(observer);}
    public void removeObserver(IPathObserver observer){ observers.remove(observer);}
    public void exitReached(Balloon balloon){
        for (IPathObserver observer: observers) {
            observer.exitReached(balloon);
        }
    }

}
