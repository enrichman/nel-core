package it.enricocandino.nel.model;

import it.enricocandino.nel.clustering.model.Clusterizable;

import java.util.List;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class SequenceClusterizable extends Clusterizable<Sequence> {

    public SequenceClusterizable(Sequence value) {
        super(value);
    }

    @Override
    public double getDistance(Clusterizable<Sequence> other) {
        double distance = 0;

        List<Point> points1 = getValue().getPoints();
        List<Point> points2 = other.getValue().getPoints();

        double count = 0;
        for(int i=0; i<points1.size(); i++) {
            if(i<points2.size()) {
                Point p1 = points1.get(i);
                Point p2 = points2.get(i);

                distance += p1.getDistance(p2);
                count++;
            }
        }

        return distance / count;
    }
}
