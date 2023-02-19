package agh;

import java.util.ArrayList;
import java.util.List;

public class Engine implements IEngine {
    private final List<IChangeObserver> observers = new ArrayList<>();
    private IPlayer player;
    private IMap map;
    private int roundNumber;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    public Engine(IPlayer player, IMap map, int roundNumber) {
        this.player = player;
        this.map = map;
        map.addObserver(this);
        this.roundNumber = roundNumber;
    }

    private void spawnBalloon(){
        if (roundNumber < 5) {
            map.placeBalloons(BalloonType.RED,1);
        } else if (roundNumber < 10) {
            map.placeBalloons(BalloonType.RED,2);
        } else if (roundNumber < 20) {
            map.placeBalloons(BalloonType.BLUE,1);
        } else if (roundNumber < 30) {
            map.placeBalloons(BalloonType.BLUE,2);
        } else {
            map.placeBalloons(BalloonType.GREEN,1);
        }
    }

    private void runRound() {
        roundNumber++;
        spawnBalloon();
        int counter = 0;
        while(map.getBalloons().length > 0) {
            if (counter < roundNumber*4){
                spawnBalloon();
                counter++;
            }
            map.moveBalloons();
            mapChanged();
            System.out.println("balloons moved");
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        pause();
    }


    @Override
    public void run() {
        while(player.getLifeCount() > 0) {
            synchronized (pauseLock) {
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException exception) {
                        break;
                    }
                }
            }
            //wait for player to start round
            runRound();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //todo koniec gry

    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void mapChanged() {
        for (IChangeObserver observer: observers) {
            observer.mapChanged();
        }
    }

    @Override
    public void exitReached(Balloon balloon) {player.decrementLife();}

    @Override
    public void balloonPopped(Balloon balloon) {player.addMoney(balloon.getDropCount());}
    public void moveTower(Tower tower, Point position) {tower.changePosition(position);}
    public void buyTower(Tower tower) {
        if (player.canBuyTower(tower.getType())){
            player.buyTower(tower.getType());
        } //todo warning
    }
    public void sellTower(Tower tower) {player.sellTower(tower.getType());}
    public void unlockTower(Tower tower) {player.unlockTower(tower.getType());}
    public void addObserver(IChangeObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(IChangeObserver observer) {
        observers.remove(observer);
    }
}
