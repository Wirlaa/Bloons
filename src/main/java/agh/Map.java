package agh;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Map implements IMap {
    private final List<IMapObserver> observers = new ArrayList<>();
    private final HashMap<Point, Tower> towers = new HashMap<>();
    private final ArrayList<Path> paths = new ArrayList<>();
    private final Multimap<Point, Balloon> balloons = HashMultimap.create();
    private static final Point LOWER_BOUND = new Point(0, 0);
    private static Point upper_bound;

    public Map(int width, int height) {
        upper_bound = new Point(width, height);
    }

    @Override
    public Balloon[] getBalloons() {
        return balloons.values().toArray(new Balloon[0]);
    }

    @Override
    public Tower[] getTowers() {
        return towers.values().toArray(new Tower[0]);
    }

    @Override
    public ArrayList<Path> getPaths() {
        return paths;
    }

    @Override
    public void moveBalloons() {
        ArrayList<Tower> activeTowers = new ArrayList<>(towers.values());
        Balloon[] activeBalloons = getBalloons();
        for(Balloon balloon : activeBalloons) {
            balloon.move();
            Tower towerToRemove = null;
            for (Tower tower : activeTowers) {
                if (checkCollisions(balloon, tower)) {
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
    public void placeBalloon(Balloon balloon) {
        if (inBounds(balloon.getPosition())) {
            balloons.put(balloon.getPosition(), balloon);
            balloon.addObserver(this);
        } //todo error
    }

    @Override
    public void placeBalloons(BalloonType type, int number) {
        int pathsCount = paths.size();
        for (int i = number; i > 0; i--) {
            placeBalloon(new Balloon(type, paths.get(ThreadLocalRandom.current().nextInt(0, pathsCount))));
        }
    }


    @Override
    public void placeTower(Tower tower) {
        if (inBounds(tower.getPosition())) {
            towers.put(tower.getPosition(), tower);
            tower.addObserver(this);
        } //todo error
    }

    @Override
    public void addPath(Path path) {paths.add(path);}

    @Override
    public boolean inBounds(Point point) {
        return point.follows(LOWER_BOUND) && point.precedes(upper_bound);
    }

    @Override
    public void addObserver(IMapObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IMapObserver observer) {
        observers.remove(observer);
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
        for (IMapObserver observer : observers) {
            observer.exitReached(balloon);
        }
    }

    public void balloonPopped(Balloon balloon) {
        balloons.remove(balloon.position, balloon);
        for (IMapObserver observer : observers) {
            observer.balloonPopped(balloon);
        }
    }

}