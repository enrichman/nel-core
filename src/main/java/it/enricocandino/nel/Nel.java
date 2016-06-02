package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.learning.LearningModule;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;
import it.enricocandino.nel.model.Stream;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Nel {

    private Map<String, Stream> streamMap = new HashMap<>();
    private Map<String, Sequence> lastSequenceMap = new HashMap<>();

    private int length;

    private ConcurrentLinkedQueue<Sequence> queue = new ConcurrentLinkedQueue<>();

    private LearningModule learningModule;
    private Thread learningModuleThread;
    private double minDistance;

    public Nel(int length, double minDistance) {
        this.minDistance = minDistance;
        this.length = length;

        this.learningModule = new LearningModule(queue);
        this.learningModuleThread = new Thread(learningModule);
        this.learningModuleThread.start();
    }

    public void stop() {
        learningModuleThread.interrupt();
    }

    public List<List<Cluster<SequenceClusterizable>>> calc() {
        List<List<Cluster<SequenceClusterizable>>> l = new ArrayList<>();

        learningModule.calc();
        for(int i=2; i<=10; i++) {
            l.add(learningModule.findMost(i, true));
        }

        return l;
    }

    public void addPoint(String streamId, Point point) {
        Stream stream = streamMap.get(streamId);
        if(stream == null) {
            stream = new Stream();
            streamMap.put(streamId, stream);
        }

        stream.getData().add(point);

        if(stream.getData().size() >= length) {
            Sequence sequence = new Sequence();
            sequence.setSeqId(streamId);
            sequence.setPoints(new ArrayList<>(stream.getData()));

            Sequence lastSubsequence = lastSequenceMap.get(streamId);

            if(lastSubsequence == null) {

                // first run, offer the new sequence
                System.out.println("Estratta sottosequenza: "+sequence);
                queue.offer(sequence);

                lastSequenceMap.put(streamId, sequence);

            } else {

                // not first run, check if the last sequence is different enough
                double distance = lastSubsequence.getDistance(sequence);
                if(distance > minDistance) {
                    System.out.println("Estratta sottosequenza: "+sequence);
                    queue.offer(sequence);

                    lastSequenceMap.put(streamId, sequence);

                    for(int i=2; i<=10; i++) {
                        // findMost(i, false);
                    }
                }
            }

            stream.clear();
            // stream.setData(stream.getData().subList(1, stream.getData().size()));
        }
    }

}
