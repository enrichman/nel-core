package it.enricocandino.nel.clustering;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public class Tree {

    private Cluster root;
    public List<Double> allPoints = new ArrayList<>();

    public void addNode(Double point) {

        allPoints.add(point);

        List<Cluster> clusters = new ArrayList<>();
        for(Double p : allPoints) {
            Cluster c = new Cluster();
            c.getPoints().add(p);
            clusters.add(c);
        }

        while(clusters.size() > 1) {

            // get closest clusters
            List<Cluster> closest = getClosest(clusters);
            Cluster c1 = closest.get(0);
            Cluster c2 = closest.get(1);

            // remove from list
            Iterator<Cluster> it = clusters.iterator();
            while(it.hasNext()) {
                Cluster c = it.next();
                if(c == c1 || c == c2) {
                    it.remove();
                }
            }

            // merge closest
            Cluster merged = new Cluster();
            merged.mergeClusters(c1, c2);

            clusters.add(merged);
        }

        root = clusters.get(0);
    }

    private List<Cluster> getClosest(List<Cluster> clusters) {
        List<Cluster> closest = new ArrayList<>();

        Cluster c1 = null,
                c2 = null;
        double minDistance = Double.MAX_VALUE;

        for(int i=0; i<clusters.size()-1; i++) {
            for(int j=i+1; j<clusters.size(); j++) {

                Cluster cluster1 = clusters.get(i);
                Cluster cluster2 = clusters.get(j);

                double distance = cluster1.getDistance(cluster2);
                if(distance < minDistance) {
                    c1 = cluster1;
                    c2 = cluster2;
                    minDistance = distance;
                }
            }
        }

        closest.add(c1);
        closest.add(c2);

        return closest;
    }

}
