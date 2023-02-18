package agh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void doesRoundWork() {
        Map map = new Map(10,10);
        Point[] points = {new Point(0,0), new Point(5,0), new Point(5,5), new Point(0,5), new Point(0,0)};
        Path path = new Path(points);
        path.addObserver(map);
        map.addPath(path);
        //map.placeTower(new Tower(TowerType.ADVANCED, new Point(1,1)));
        Player player = new Player("player", 100);
        Engine engine = new Engine(player, map, 1);
        engine.run();

        //nie za bardzo da sie przetestowac bez wizualizacji
    }
}