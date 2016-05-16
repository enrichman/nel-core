package it.enricocandino.nel;

import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Nel {

    private Stream<Point> stream;
    private int length;

    private List<List<Point>> subsequences = new ArrayList<List<Point>>();

    public Nel(int length) {
        this.stream = new Stream<Point>();
        this.length = length;
    }

    public List<List<Point>> getSubsequences() {
        return subsequences;
    }

    public void addPoint(Point point) {
        List<Point> data = stream.getData();
        data.add(point);

        if(data.size() >= length) {
            List<Point> subsequence = new ArrayList<Point>(data.subList(data.size() - length, data.size()));
            subsequences.add(subsequence);
        }
    }

}
