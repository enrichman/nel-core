package it.enricocandino.nel.clustering;

import it.enricocandino.nel.clustering.model.Clusterizable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class Cluster<T extends Clusterizable> {

    // distance between the two clusters
    private double height;
    private Cluster<T> cluster1;
    private Cluster<T> cluster2;

    private List<Clusterizable> points = new ArrayList<>();

    public void mergeClusters(Cluster<T> c1, Cluster<T> c2) {
        cluster1 = c1;
        cluster2 = c2;

        points.addAll(c1.getPoints());
        points.addAll(c2.getPoints());

        height = c1.getDistance(c2);
    }

    public List<Clusterizable> getPoints() {
        return points;
    }

    public double getDistance(Cluster<T> other) {
        // cerco la distanza minima tra i miei punti e quelli dell'altro cluster
        double min = Double.MAX_VALUE;
        for(Clusterizable myPoint : points) {
            for(Clusterizable otherPoint : other.getPoints()) {
                double distance = myPoint.getDistance(otherPoint);
                // Math.sqrt(Math.pow(myPoint - otherPoint, 2));
                if(distance < min)
                    min = distance;
            }
        }
        return min;
    }

}
