package agh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void detectCircleCollision() {
        Point point = new Point(0,0);
        assertTrue(point.detectCircleCollision(3, new Point(1,1), 1));
        assertFalse(point.detectCircleCollision(3, new Point(5,5), 1));
        assertFalse(point.detectCircleCollision(3, new Point(0,4), 1));
        assertTrue(point.detectCircleCollision(3, new Point(0,0), 3));
    }

    @Test
    void compare() {
        Point point = new Point(0,0);
        assertTrue(point.precedes(new Point(1,0)));
        assertTrue(point.precedes(new Point(0,1)));
        assertTrue(point.follows(new Point(-1,0)));
        assertTrue(point.follows(new Point(0,-1)));
    }
}