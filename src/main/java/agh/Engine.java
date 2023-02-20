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
        if (roundNumber < 2) {
            map.placeBalloons(BalloonType.RED,1);
        } else if (roundNumber < 4) {
            map.placeBalloons(BalloonType.RED,2);
        } else if (roundNumber < 6) {
            map.placeBalloons(BalloonType.BLUE,1);
        } else if (roundNumber < 8) {
            map.placeBalloons(BalloonType.BLUE,2);
        } else {
            map.placeBalloons(BalloonType.GREEN,1);
        }
    }

    private void runRound() {
        roundNumber++;
        notifyNewRound();
        spawnBalloon();
        int counter = 0;
        while(map.getBalloons().length > 0) {
            if (counter < roundNumber*4){
                spawnBalloon();
                counter++;
            }
            map.moveBalloons();
            mapChanged();
            //System.out.println("balloons moved");
            try {
                Thread.sleep(100);
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
    public void exitReached(Balloon balloon) {
        player.decrementLife();
        notifyLifeLost();
    }

    @Override
    public void balloonPopped(Balloon balloon) {
        player.addMoney(balloon.getDropCount());
        notifyMoneyChanged();
    }
    public void moveTower(Tower tower, Point position) {tower.changePosition(position);}
    public boolean isUnlocked(TowerType type) {return player.isUnlocked(type);}
    public boolean buyTower(Tower tower) {
        if (player.canBuyTower(tower.getType())){
            player.buyTower(tower.getType());
            notifyMoneyChanged();
            return true;
        }
        return false;
    }
    public void sellTower(Tower tower) {player.sellTower(tower.getType());}
    public boolean unlockTower(TowerType towerType) {
        if (player.unlockTower(towerType)){
            notifyMoneyChanged();
            return true;
        }
        System.out.println(player.getMoney());
        return false;
    }
    public void addObserver(IChangeObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(IChangeObserver observer) {
        observers.remove(observer);
    }
    public void notifyLifeLost(){
        for(IChangeObserver observer:observers){
            observer.lifeLost();
        }
    }
    public void notifyNewRound(){
        for(IChangeObserver observer:observers){
            observer.newRound();
        }
    }
    public void notifyMoneyChanged(){
        for(IChangeObserver observer:observers){
            observer.moneyChanged(player.getMoney());
        }
    }

}
