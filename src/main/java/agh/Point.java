package agh;

/**
 * Record representing positions of map elements.
 */
public record Point(double x, double y) {
    public boolean precedes(Point other) {
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Point other) {
        return this.x >= other.x && this.y >= other.y;
    }
    public boolean detectCircleCollision(double radius1, Point position2, double radius2) {
        double distance = Math.hypot(Math.abs(position2.y - this.y), Math.abs(position2.x - this.x));
        return distance < radius1 + radius2;
    }
}
