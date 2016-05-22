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
                findMost();
            }

            stream.setData(stream.getData().subList(1, stream.getData().size()));
        }
    }

    public void findMost() {
        List<Cluster<SequenceClusterizable>> most = dendo.findMostSignificative(6);
        most.sort(new Comparator<Cluster>() {
            @Override
            public int compare(Cluster o1, Cluster o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });

        if(!most.isEmpty()) {
            Cluster<SequenceClusterizable> top = most.get(0);
            List<SequenceClusterizable> sList = top.getPoints();

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

            System.out.println("\n########\n# Height: " +top.getHeight()+"\n#########\n");
        }

        System.out.println();
    }

}
