package agh;

// ruszanie balonow //done
// wzorzec obserwator zeby trackowac smierci balonow //done
// funkcja informujaca playera ze stracil zycie //done
// funkcja informujaca playera ze dostal hajs
// licznik rund
// ogarnianie pojawiania sie balonow

public class Engine implements IEngine, IPathObserver {
    private Player player;
    private Map map;
    private int roundNumber;

    public Engine(Player player, Map map, int roundNumber) {
        this.player = player;
        this.map = map;
        this.roundNumber = roundNumber;

        //todo na razie hard coded, powinno byc przy inicjalizacji mapy
        Point[] points = {new Point(0,0), new Point(5,0), new Point(5,5), new Point(0,5)};
        Path path = new Path(points);
        map.addPath(path);
    }

    private void runRound() {
        //inizjalizacja rundy (tworzenie i dodawanie balonow do mapy)

        // powtarzaj
        map.moveBalloons(1);
        // az do konca balonow
        roundNumber++;

        // trzeba dodac wyjatki jak graczowi zabraknie zycia
        // trzeba dodac mechanike z pieniedzmi

    }


    @Override
    public void run() {

    }

    @Override
    public void exitReached(Balloon balloon) {
        player.decrementLife();
    }
}
