package it.enricocandino.nel.model;

import it.enricocandino.nel.clustering.model.Clusterizable;

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

        Sequence sequence = getValue();
        Sequence otherSequence = other.getValue();

        double count = 0;
        for(Point p1 : sequence.getPoints()) {
            for(Point p2 : otherSequence.getPoints()) {
                if(p1.getX().equals(p2.getX())) {
                    distance += p1.getDistance(p2);
                    count++;
                }
            }
        }

        return distance / count;
    }
}
