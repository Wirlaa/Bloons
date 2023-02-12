package agh;

public class Engine implements IEngine {
    private IPlayer player;
    private IMap map;
    private int roundNumber;

    public Engine(IPlayer player, IMap map, int roundNumber) {
        this.player = player;
        this.map = map;
        map.addObserver(this);
        this.roundNumber = roundNumber;
    }

    private void initRound(){
        if (roundNumber < 10) {
            map.placeBalloons(BalloonType.RED, roundNumber*5);
        } else if (roundNumber < 20) {
            map.placeBalloons(BalloonType.BLUE, (roundNumber-9)*3);
        } else {
            map.placeBalloons(BalloonType.GREEN, (roundNumber-19)*2);
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
}
