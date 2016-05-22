package it.enricocandino.nel.model;

import java.util.List;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class Sequence {

    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getDistance(Sequence other) {
        double distance = 0;

        double count = 0;
        for(int i=0; i<getPoints().size(); i++) {
            if(i < other.getPoints().size()) {
                Point p1 = getPoints().get(i);
                Point p2 = other.getPoints().get(i);

                distance += p1.getDistance(p2);
                count++;
            }
        }

        return distance / count;
    }

    @Override
    public String toString() {
        Point first = points.get(0);
        Point last = points.get(points.size()-1);
        return "Sequence ["+first.getX()+"]-["+last.getX()+"]";
    }
}
