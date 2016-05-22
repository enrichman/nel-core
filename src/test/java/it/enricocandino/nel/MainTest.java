package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.clustering.model.Clusterizable;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

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
                    round(i, 2),
                    round(Math.cos(i), 3)
            ));
        }

        Tree dendo = new Tree();

        for(Sequence seq : nel.getSubsequences()) {
            SequenceClusterizable seqCluster = new SequenceClusterizable(seq);
            dendo.addNode(seqCluster);
        }

        List<Cluster<SequenceClusterizable>> most = dendo.findMostSignificative(4);
        most.sort(new Comparator<Cluster>() {
            @Override
            public int compare(Cluster o1, Cluster o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });

        Cluster<SequenceClusterizable> top = most.get(1);
        List<SequenceClusterizable> sList = top.getPoints();

        for(SequenceClusterizable sc : sList) {
            System.out.println("Seq");

        }

        Sequence s1 = sList.get(0).getValue();
        Sequence s2 = sList.get(1).getValue();
        Sequence s3 = sList.get(2).getValue();
        Sequence s4 = sList.get(3).getValue();

        for(int i=0; i<s1.getPoints().size(); i++) {
            DecimalFormat df = new DecimalFormat("#.##########");
            String val1 = df.format(s1.getPoints().get(i).getY());
            String val2 = df.format(s2.getPoints().get(i).getY());
            String val3 = df.format(s3.getPoints().get(i).getY());
            String val4 = df.format(s4.getPoints().get(i).getY());
            String tot = ("\""+i+"\",\""+val1+"\",\""+val2+"\",\""+val3+"\",\""+val4+"\"");
            System.out.println(tot.replace(".", ","));
        }
        System.out.println();

        System.out.println();
    }

    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
