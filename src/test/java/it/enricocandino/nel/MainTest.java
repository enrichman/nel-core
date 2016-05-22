package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;
import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class MainTest extends TestCase {

    public void testMe() throws Exception {

        Nel nel = new Nel(100);

        for(double i=0.; i<10.; i+=0.1) {
            nel.addPoint(new Point(
                    round(i, 2),
                    round(Math.cos(i), 3)
            ));
        }

        for(double i=0.; i<10.; i+=0.1) {
            nel.addPoint(new Point(
                    round(i, 2),
                    round(Math.cos(i), 3)
            ));
        }

        Tree dendo = new Tree();

        for(Sequence seq : nel.getSubsequences()) {
            SequenceClusterizable seqCluster = new SequenceClusterizable(seq);
            dendo.addNode(seqCluster);
        }

        System.out.println();
    }

    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
