package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.model.DoubleClusterizable;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.PointClusterizable;
import junit.framework.TestCase;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class TreeTest extends TestCase {

    public void testAdd() {

        Tree treePoints = new Tree();
        treePoints.addNode(new PointClusterizable(new Point(0., 0.)));
        treePoints.addNode(new PointClusterizable(new Point(10., 10.)));
        treePoints.addNode(new PointClusterizable(new Point(1., 1.)));

        Tree tree = new Tree();
        tree.addNode(new DoubleClusterizable(1.));
        tree.addNode(new DoubleClusterizable(30.));
        tree.addNode(new DoubleClusterizable(3.));
        tree.addNode(new DoubleClusterizable(4.));
        tree.addNode(new DoubleClusterizable(5.));
        tree.addNode(new DoubleClusterizable(6.));
        tree.addNode(new DoubleClusterizable(36.));

        System.out.println();
    }

    public void testDistance() {

        Cluster<DoubleClusterizable> cluster1 = new Cluster<>();
        cluster1.getPoints().add(new DoubleClusterizable(1.));

        Cluster<DoubleClusterizable> cluster2 = new Cluster<>();
        cluster2.getPoints().add(new DoubleClusterizable(4.));

        Cluster<DoubleClusterizable> cluster3 = new Cluster<>();
        cluster3.getPoints().add(new DoubleClusterizable(30.));

        double d12 = cluster1.getDistance(cluster2);
        double d13 = cluster1.getDistance(cluster3);
        double d23 = cluster2.getDistance(cluster3);

        System.out.println(d12);
        System.out.println(d13);
        System.out.println(d23);

    }

}
