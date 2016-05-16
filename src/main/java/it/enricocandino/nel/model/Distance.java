package it.enricocandino.nel.model;

import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Distance {

    public static double distance(List<Point> s1, List<Point> s2) {
        double distance = 0;

        for(int i=0; i<s1.size(); i++) {
            Point p1 = s1.get(i);
            Point p2 = s2.get(i);

            distance += p1.getDistance(p2);
        }

        return distance / s1.size();
    }
}
