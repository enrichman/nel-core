package it.enricocandino.nel.learning;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Enrico Candino on 25/05/16.
 */
public class LearningModule implements Runnable {

    private ConcurrentLinkedQueue<Sequence> queue;
    private Tree<SequenceClusterizable> tree;

    public LearningModule(ConcurrentLinkedQueue<Sequence> queue) {
        this.queue = queue;
        this.tree = new Tree<>();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                Sequence sequence = queue.poll();

                if(sequence != null) {
                    // new sequence added
                    tree.addNode(new SequenceClusterizable(sequence));

                } else {
                    // empty queue, wait a bit
                    Thread.sleep(100);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Cluster<SequenceClusterizable>> findMost(int maxSubTreeSize, boolean print) {
        List<Cluster<SequenceClusterizable>> most = tree.findMostSignificative(maxSubTreeSize);
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
