package it.enricocandino.nel.clustering;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class Cluster {

    // distance between the two clusters
    private double height;
    private Cluster cluster1;
    private Cluster cluster2;

    private List<Double> points = new ArrayList<>();

    public void mergeClusters(Cluster c1, Cluster c2) {
        cluster1 = c1;
        cluster2 = c2;

        points.addAll(c1.getPoints());
        points.addAll(c2.getPoints());

        height = c1.getDistance(c2);
    }

    public List<Double> getPoints() {
        return points;
    }

    public double getDistance(Cluster other) {
        // cerco la distanza minima tra i miei punti e quelli dell'altro cluster
        double min = Double.MAX_VALUE;
        for(Double myPoint : points) {
            for(Double otherPoint : other.getPoints()) {
                double distance = Math.sqrt(Math.pow(myPoint - otherPoint, 2));
                if(distance < min)
                    min = distance;
            }
        }
        return min;
    }

}
