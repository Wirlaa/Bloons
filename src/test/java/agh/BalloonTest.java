package agh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalloonTest {
    @Test
    void doesBalloonMoveCorrectly() {
        Point[] points = {new Point(0,0), new Point(1,0), new Point(1,1), new Point(0,1)};
        Path path = new Path(points);
        Balloon balloon = new Balloon(BalloonType.RED, path);

        assertEquals(new Point(0,0), balloon.position);
        balloon.move();
        assertEquals(new Point(1,0), balloon.position);
        balloon.move();
        assertEquals(new Point(1,1), balloon.position);
        balloon.move();
        assertEquals(new Point(0,1), balloon.position);
    }

    @Test
    void test() {
        Point[] points = {new Point(0,0), new Point(24,35), new Point(25,26), new Point(0,1)};
        Path path = new Path(points);
        Balloon balloon = new Balloon(BalloonType.RED, path);

        while(true) {
            System.out.println(balloon.getPosition());
            balloon.move();
            System.out.println(balloon.getPosition());
        }
    }

    @Test
    void initCheck() {
        Point[] points = {new Point(0,0), new Point(1,0), new Point(1,1), new Point(0,1)};
        Path path = new Path(points);
        Balloon balloon1 = new Balloon(BalloonType.BLUE, path);
        balloon1.move();
        Balloon balloon2 = new Balloon(balloon1);
        assertEquals(balloon1.position, balloon2.position);
    }
}