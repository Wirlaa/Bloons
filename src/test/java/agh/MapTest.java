package agh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void isInBounds() {
        Map map = new Map(10,10);
        assertTrue(map.inBounds(new Point(0,0)));
        assertTrue(map.inBounds(new Point(3,5)));
        assertTrue(map.inBounds(new Point(10,0)));
        assertTrue(map.inBounds(new Point(0,10)));
        assertTrue(map.inBounds(new Point(10,10)));
        assertFalse(map.inBounds(new Point(11,11)));
    }

    @Test
    void isSpawningBalloonsWorking() {
        Map map = new Map(10,10);
        Point[] points = {new Point(0,0), new Point(1,0), new Point(1,1), new Point(0,1)};
        Path path = new Path(points);
        map.addPath(path);
        path.addObserver(map);
        map.placeBalloons(BalloonType.RED, 10);
        assertEquals(10, map.getBalloons().length);
    }

    @Test
    void isPlacingTowerWorking() {
        Map map = new Map(10,10);
        map.placeTower(new Tower(TowerType.BASIC, new Point(3,3)));
        map.placeTower(new Tower(TowerType.BASIC, new Point(-1,-1)));
        assertEquals(1, map.getTowers().length);
    }

    @Test
    void isMoveBalloonsWorking() {
        Map map = new Map(10,10);
        Point[] points = {new Point(0,0), new Point(1,0), new Point(1,1), new Point(0,1)};
        Path path = new Path(points);
        map.addPath(path);
        path.addObserver(map);
        map.placeBalloons(BalloonType.BLUE, 5);
        map.placeTower(new Tower(TowerType.ADVANCED, new Point(0.5,0.5)));
        assertEquals(5, map.getBalloons().length);
        map.moveBalloons();
        assertEquals(9, map.getBalloons().length);
    }
}