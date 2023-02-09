package agh;

import java.util.ArrayList;
import java.util.HashMap;

// zmienna ścieżki - lista klasy sciezka

// kolekcja przechowujaca aktualne balony i ich pozycje
// wzorzec obserwator zeby trackowac pozycje balonow

// kolekcja pozycji wiezyczek

public class Map implements IMap, IMapElementObserver, IPathObserver {
    private final HashMap<Point, Tower> towers = new HashMap<>();
    private final ArrayList<Path> paths = new ArrayList<>();
    private final HashMap<Point, Balloon> balloons = new HashMap<>();
    private static final Point LOWER_BOUND = new Point(0,0);
    private static Point upper_bound;

    public Map(int width, int height) {
        upper_bound = new Point(width, height);
    }

    public void moveBalloons(double step) {
        ArrayList<Tower> activeTowers = new ArrayList<>(towers.values());
        for (Balloon balloon: balloons.values()){
            balloon.move(step);
            checkCollisions(activeTowers, balloon);
        }
    }

    //na razie brutem przechodze po wszystkich wiezyczkach, da sie ladniej
    // hard coded radius balonika jako 1!
    // jak chcemy firing rate to trzeba bedzie inaczej zrobic, na razie kazda wiezyczka strzela raz na tick
    public void checkCollisions(ArrayList<Tower> activeTowers, Balloon balloon) {
        Tower towerToRemove = null;
        for (Tower tower : activeTowers){
            if(balloon.position.detectCircleCollision(1, tower.position, tower.getRange())) {
                balloons.remove(balloon.position, balloon);
                //observer do engine mowiacy ze player dostal hajs
                towerToRemove = tower;
                break;
            }
        }
        activeTowers.remove(towerToRemove);
    }

    public void placeBalloon(Balloon balloon){
        if (inBounds(balloon.getPosition())) {
            balloons.put(balloon.getPosition(), balloon);
            balloon.addObserver(this);
        } //todo error
    }

    public void placeTower(Tower tower){
        if (inBounds(tower.getPosition())) {
            towers.put(tower.getPosition(), tower);
            tower.addObserver(this);
        } //todo error
    }

    public void addPath(Path path){
        paths.add(path);
    }

    //testyyyy
    public boolean inBounds(Point point){
        return point.compareTo(LOWER_BOUND) > 0 && point.compareTo(upper_bound) < 0;
    }

    @Override
    public void positionChangedTower(Point oldPosition, Point newPosition, Tower tower) {
        towers.remove(oldPosition, tower);
        towers.put(newPosition, tower);
    }

    @Override
    public void positionChangedBalloon(Point oldPosition, Point newPosition, Balloon balloon) {
        balloons.remove(oldPosition, balloon);
        balloons.put(newPosition, balloon);
    }

    @Override
    public void exitReached(Balloon balloon) {
        balloons.remove(balloon.position, balloon);
    }
}
