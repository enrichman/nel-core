package it.enricocandino.nel.model;

import it.enricocandino.nel.clustering.model.Clusterizable;

/**
 * Created by Enrico Candino on 22/05/16.
 */
public class DoubleClusterizable extends Clusterizable<Double> {

    public DoubleClusterizable(Double value) {
        super(value);
    }

    @Override
    public double getDistance(Clusterizable<Double> other) {
        return Math.sqrt(Math.pow(other.getValue() - getValue(), 2));
    }

}
