package it.enricocandino.nel;

import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.clustering.Tree;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.SequenceClusterizable;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Enrico Candino on 22/05/16.
 */
public class CsvTest extends TestCase {

    public void testCSV() throws Exception {

        Nel nel = new Nel(150, 5);

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/samples.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(count > 1) {
                    nel.addPoint(new Point((double) count, Double.parseDouble(line.split(",")[1])));
                }
                count++;
            }
        }

        System.out.println();

    }

}
