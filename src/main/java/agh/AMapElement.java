package agh;

import java.util.ArrayList;

/**
 * Abstract class for basic properties of map elements.
 */
public abstract class AMapElement {
    protected Point position;
    protected final ArrayList<IMapElementObserver> observers = new ArrayList<>();
    public Point getPosition() {return position;}
    public void addObserver(IMapElementObserver observer){ observers.add(observer);}
    public void removeObserver(IMapElementObserver observer){ observers.remove(observer);}
    abstract void positionChanged(Point oldPosition, Point newPosition);
}
