package agh;

import java.util.concurrent.ThreadLocalRandom;

public class Engine implements IEngine, IMapObserver {
    private Player player;
    private Map map;
    private int roundNumber;

    public Engine(Player player, Map map, int roundNumber) {
        this.player = player;
        this.map = map;
        map.addObserver(this);
        this.roundNumber = roundNumber;


        //todo na razie hard coded, powinno byc przy inicjalizacji mapy
        Point[] points = {new Point(0,0), new Point(5,0), new Point(5,5), new Point(0,5)};
        Path path = new Path(points);
        path.addObserver(map);
        map.addPath(path);
    }

    private void initRound(){
        if (roundNumber < 10) {
            placeBalloons(BalloonType.RED, roundNumber*5);
        } else if (roundNumber < 20) {
            placeBalloons(BalloonType.BLUE, (roundNumber-9)*3);
        } else {
            placeBalloons(BalloonType.GREEN, (roundNumber-19)*2);
        }
    }

    private void placeBalloons(BalloonType type, int number){
        int pathsCount = map.getPaths().size();
        for (int i = number; i > 0; i--) {
            map.placeBalloon(new Balloon(type, map.getPaths().get(ThreadLocalRandom.current().nextInt(0, pathsCount))));
        }
    }

    private void runRound() {
        initRound();
        while(map.getBalloons().length > 0) {
            // moze jakis sleep
            map.moveBalloons();
        }
        roundNumber++;
    }


    @Override
    public void run() {
        while(player.getLifeCount() > 0) {
            //wait for player to start round
            runRound();
        }

    }

    @Override
    public void exitReached(Balloon balloon) { player.decrementLife();}

    @Override
    public void balloonPopped(Balloon balloon) { player.addMoney(balloon.getDropCount());}

    @Override
    public void buyTower(Tower tower) { player.buyTower(tower.getType());}  //nie ma warningu

    @Override
    public void sellTower(Tower tower) { player.sellTower(tower.getType());}

    @Override
    public void unlockTower(Tower tower) { player.unlockTower(tower.getType());}
}
