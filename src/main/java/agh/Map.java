package agh;

import java.util.*;

public class Map implements IMap, IMapElementObserver, IPathObserver {
    private final List<IMapObserver> observers = new ArrayList<>();
    private final HashMap<Point, Tower> towers = new HashMap<>();
    private final ArrayList<Path> paths = new ArrayList<>();
    private final HashMap<Point, Balloon> balloons = new HashMap<>();
    private static final Point LOWER_BOUND = new Point(0,0);
    private static Point upper_bound;

    public Map(int width, int height) {
        upper_bound = new Point(width, height);
    }

    @Override
    public Balloon[] getBalloons() {
        return (Balloon[]) balloons.values().stream().toArray(); //cast?
        //Warning:(19, 55) Can be replaced with 'collection.toArray()' ???
    }

    @Override
    public ArrayList<Path> getPaths() {return paths;}

    @Override
    public void moveBalloons() {
        //czy to wywali concurrent modification?
        ArrayList<Tower> activeTowers = new ArrayList<>(towers.values());
        for (Balloon balloon: balloons.values()){
            balloon.move(balloon.getSpeed());
            Tower towerToRemove = null;
            for (Tower tower : activeTowers){
                if (checkCollisions(balloon, tower)){
                    balloons.remove(balloon.position, balloon);
                    balloonPopped(balloon);
                    spawnNextBalloons(balloon);
                    towerToRemove = tower;
                    break;
                }
            }
            activeTowers.remove(towerToRemove);
        }
    }

    private void spawnNextBalloons(Balloon balloon) {
        for (int i = balloon.getSpawnCount(); i > 0; i--) {
            placeBalloon(new Balloon(balloon));
        }
    }

    //na razie brutem przechodze po wszystkich wiezyczkach, da sie ladniej
    // hard coded radius balonika jako 1!
    // jak chcemy firing rate to trzeba bedzie inaczej zrobic, na razie kazda wiezyczka strzela raz na tick
    private boolean checkCollisions(Balloon balloon, Tower tower) {
        return balloon.position.detectCircleCollision(1, tower.position, tower.getRange());
    }

    @Override
    public void placeBalloon(Balloon balloon){
        if (inBounds(balloon.getPosition())) {
            balloons.put(balloon.getPosition(), balloon);
            balloon.addObserver(this);
        } //todo error
    }

    @Override
    public void placeTower(Tower tower){
        if (inBounds(tower.getPosition())) {
            towers.put(tower.getPosition(), tower);
            tower.addObserver(this);
        } //todo error
    }

    @Override
    public void addPath(Path path) {paths.add(path);}

    //testyyyy
    private boolean inBounds(Point point){
        return point.compareTo(LOWER_BOUND) > 0 && point.compareTo(upper_bound) < 0;
    }

    @Override
    public void addObserver(IMapObserver observer) {observers.add(observer);}
    @Override
    public void removeObserver(IMapObserver observer) {observers.remove(observer);}

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
        for (IMapObserver observer: observers) {
            observer.exitReached(balloon);
        }
    }

    public void balloonPopped(Balloon balloon){
        for (IMapObserver observer: observers) {
            observer.balloonPopped(balloon);
        }
    }

    public void moveTower(Tower tower, Point position) {
        tower.changePosition(position);
    } //troche glupie ze mapa wywoluje zmiane a potem jako observer obserwuje tą zmiane XD
    //chyba ze player powinien miec opcje move, wtedy to by mialo jakis sens


    //ogolnie moze wystarczy ze player bedzie mogl ogarniac wiezyczki, ale nie wiem jak to z gui wyjdzie
    public void buyTower(Tower tower) {
        for (IMapObserver observer: observers) {
            observer.buyTower(tower);
        }
    }

    public void sellTower(Tower tower) {
        for (IMapObserver observer: observers) {
            observer.sellTower(tower);
        }
    }

    public void unlockTower(Tower tower) {
        for (IMapObserver observer: observers) {
            observer.unlockTower(tower);
        }
    }
}
