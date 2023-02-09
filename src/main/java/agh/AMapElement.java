package agh;

import java.util.ArrayList;
import java.util.List;

public abstract class AMapElement {
    protected Point position;
    protected final List<IMapElementObserver> observers = new ArrayList<>();
    public Point getPosition() {return position;}
    public void addObserver(IMapElementObserver observer){ observers.add(observer);}
    public void removeObserver(IMapElementObserver observer){ observers.remove(observer);}
    abstract void positionChanged(Point oldPosition, Point newPosition);
}
