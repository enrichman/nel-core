package it.enricocandino.nel.model;

import java.util.Date;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class BasicPoint extends Point<Float, Float> {

    @Override
    public double getDistance(Point<Float, Float> other) {
        return Math.sqrt(Math.pow(getY() - other.getY(), 2));
    }
}
