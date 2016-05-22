package it.enricocandino.nel;

import it.enricocandino.nel.model.Point;
import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class MainTest extends TestCase {

    public void testMe() throws Exception {

        Nel nel = new Nel(10);

        for(double i=0.; i<100.; i+=0.1) {
            nel.addPoint(new Point(
                    round(i, 1),
                    round(Math.cos(i), 3)
            ));
        }



        System.out.println();
    }

    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
