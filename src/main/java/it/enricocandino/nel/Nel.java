package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;
import it.enricocandino.nel.model.Stream;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Nel {

    private Stream stream;
    private int length;
    private Tree dendo = new Tree();

    private List<Sequence> subsequences = new ArrayList<>();
    private double minDistance;

    public Nel(int length, double minDistance) {
        this.minDistance = minDistance;
        this.stream = new Stream();
        this.length = length;
    }

    public List<Sequence> getSubsequences() {
        return subsequences;
    }

    public void addPoint(Point point) {
        stream.getData().add(point);

        if(stream.getData().size() >= length) {
            Sequence sequence = new Sequence();
            sequence.setPoints(new ArrayList<>(stream.getData()));

            if(subsequences.isEmpty()) {
                System.out.println("Estratta sottosequenza: "+sequence);
                subsequences.add(sequence);
                dendo.addNode(new SequenceClusterizable(sequence));
            }

            Sequence last = subsequences.get(subsequences.size()-1);
            double distance = last.getDistance(sequence);
            if(distance > minDistance) {
                System.out.println("Estratta sottosequenza: "+sequence);
                subsequences.add(sequence);
                dendo.addNode(new SequenceClusterizable(sequence));

                for(int i=2; i<=10; i++) {
                    findMost(i, false);
                }
            }

            stream.setData(stream.getData().subList(1, stream.getData().size()));
        }
    }

    public List<Cluster<SequenceClusterizable>> findMost(int maxSubTreeSize, boolean print) {
        List<Cluster<SequenceClusterizable>> most = dendo.findMostSignificative(maxSubTreeSize);
        most.sort(new Comparator<Cluster>() {
            @Override
            public int compare(Cluster o1, Cluster o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });

        if(!most.isEmpty()) {
            Cluster<SequenceClusterizable> top = most.get(0);
            List<SequenceClusterizable> sList = top.getPoints();
            System.out.println("Cluster height: " +top.getHeight());

            if(print) {
                DecimalFormat df = new DecimalFormat("#.##########");

                StringBuilder sb = new StringBuilder();
                for(int i=0; i<sList.get(0).getValue().getPoints().size(); i++) {
                    sb.append('"').append(i).append('"');
                    for(int j=0; j<maxSubTreeSize; j++) {
                        Sequence sequence = sList.get(j).getValue();
                        String val1 = df.format(sequence.getPoints().get(i).getY());
                        sb.append(',').append('"').append(val1).append('"');
                    }
                    sb.append('\n');
                }
                System.out.println(sb.toString().replace('.', ','));
            }
        }

        return most;
    }

}
