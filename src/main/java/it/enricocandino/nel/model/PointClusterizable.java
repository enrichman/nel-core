package it.enricocandino.nel.model;

import it.enricocandino.nel.clustering.model.Clusterizable;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class PointClusterizable extends Clusterizable<Point> {

    public PointClusterizable(Point value) {
        super(value);
    }

    @Override
    public double getDistance(Clusterizable<Point> other) {
        return getValue().getDistance(other.getValue());
    }
}
