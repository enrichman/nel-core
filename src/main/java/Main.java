import it.enricocandino.nel.Nel;
import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.SequenceClusterizable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Enrico Candino on 25/05/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Nel nel = new Nel(200, 150);

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/samples.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(count > 1) {
                    nel.addPoint("A", new Point((double) count, Double.parseDouble(line.split(",")[1])));
                }
                count++;
            }
        }

        Map<Integer,List<Cluster<SequenceClusterizable>>> map = new HashMap<>();
        for(int i=2; i<=10; i++) {
            // map.put(i, nel.findMost(i, true));
        }

        System.out.println("Ended");
    }

}
