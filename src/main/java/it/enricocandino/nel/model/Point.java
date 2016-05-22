package it.enricocandino.nel.model;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Point {

    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public double getDistance(Point other) {
        double dx = Math.pow(x - other.getX(), 2);
        double dy = Math.pow(y - other.getY(), 2);
        return Math.sqrt(dx + dy);
    }
}
