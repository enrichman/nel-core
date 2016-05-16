package it.enricocandino.nel;

import it.enricocandino.nel.model.BasicPoint;
import it.enricocandino.nel.model.Distance;
import it.enricocandino.nel.model.Point;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class MainTest extends TestCase {

    public void testMe() throws Exception {

        Nel nel = new Nel(10);

        for(float i=0; i<100; i+=0.1) {
            BasicPoint p = new BasicPoint();
            p.setX(round(i, 1));
            p.setY(round((float) Math.cos(i), 3));

            nel.addPoint(p);
        }

        for(int i=0; i<nel.getSubsequences().size()-1; i++) {
            for(int j=i+1; j<nel.getSubsequences().size(); j++) {
                List<Point> s1 = nel.getSubsequences().get(i);
                List<Point> s2 = nel.getSubsequences().get(j);

                System.out.println("["+i+"]-["+j+"] - "+Distance.distance(s1, s2));
            }
        }

        System.out.println();
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
