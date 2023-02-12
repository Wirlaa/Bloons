package agh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {
    @Test
    void isYCorrectHorizontal() {
        Point[] points = {new Point(0,0), new Point(5,0)};
        Path path = new Path(points);
        double newY = path.getY(new Point(0,0), 1, 1);
        assertEquals(0, newY);
    }

    @Test
    void isYCorrectVertical() {
        Point[] points = {new Point(0,0), new Point(0,5)};
        Path path = new Path(points);
        double newY = path.getY(new Point(0,0), 1, 1);
        assertEquals(1, newY);
    }

    @Test
    void isYCorrectLinear() {
        Point[] points = {new Point(0,0), new Point(5,5)};
        Path path = new Path(points);
        double newY = path.getY(new Point(0,0), 1, 1);
        assertEquals(1, newY);
        newY = path.getY(new Point(0,0), 1, 3);
        assertEquals(3, newY);
        newY = path.getY(new Point(0,0), 1, -0.5);
        assertEquals(-0.5, newY);
    }

    @Test
    void isIndexCheckWorking() {
        Point[] points = {new Point(0,0), new Point(2,0), new Point(2,2)};
        Path path = new Path(points);
        assertTrue(path.isNewPositionBetweenPathPoints(new Point(1,0), new Point(1.5, 0), 1));
        assertFalse(path.isNewPositionBetweenPathPoints(new Point(1,0), new Point(2, 0), 1));
        assertFalse(path.isNewPositionBetweenPathPoints(new Point(1,0), new Point(3, 0), 1));
        assertTrue(path.isNewPositionBetweenPathPoints(new Point(2,-1), new Point(2, 1), 2));
        assertFalse(path.isNewPositionBetweenPathPoints(new Point(2,0), new Point(3, 3), 2));
    }
}