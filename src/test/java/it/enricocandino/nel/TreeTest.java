package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.dendogram.Dendogram;
import it.enricocandino.nel.dendogram.Node;
import junit.framework.TestCase;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class TreeTest extends TestCase {

    public void testAdd() {

        Tree tree = new Tree();
        tree.addNode(1.);
        tree.addNode(30.);
        tree.addNode(3.);
        tree.addNode(4.);
        tree.addNode(5.);
        tree.addNode(6.);
        tree.addNode(36.);

        System.out.println();
    }

    public void testDistance() {

        Cluster cluster1 = new Cluster();
        cluster1.getPoints().add(1.);

        Cluster cluster2 = new Cluster();
        cluster2.getPoints().add(4.);

        Cluster cluster3 = new Cluster();
        cluster3.getPoints().add(30.);

        double d12 = cluster1.getDistance(cluster2);
        double d13 = cluster1.getDistance(cluster3);
        double d23 = cluster2.getDistance(cluster3);

        System.out.println(d12);
        System.out.println(d13);
        System.out.println(d23);

    }

}
