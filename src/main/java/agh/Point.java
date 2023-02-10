package agh;

//Warning:(5, 52) Raw use of parameterized class 'Comparable' ???
//cos nie wyszlo z tym comparable

/**
 * Record representing positions of map elements.
 */
public record Point(double x, double y) implements Comparable {

    @Override
    public int compareTo(Object o) {
        Point point = (Point) o; //castowanie chyba trzeba? jak to ladniej zrobic?
        if (Double.compare(this.x(), point.x()) != 0) {
            return Double.compare(this.x(), point.x());
        } else return Double.compare(this.y(), point.y());
    }

    //testyyy
    public boolean detectCircleCollision(double radius1, Point position2, double radius2) {
        double distance = Math.hypot(Math.abs(position2.y() - this.y()), Math.abs(position2.x() - this.x()));
        return distance < radius1 + radius2;
    }
}
