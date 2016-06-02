import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import it.enricocandino.nel.Nel;
import it.enricocandino.nel.SequencePreprocessor;
import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.model.DatePoint;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.SequenceClusterizable;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Enrico Candino on 25/05/16.
 */
public class MongoStreamReader2 {

    private static List<DatePoint> datePoints = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final Nel nel = new Nel(24, 150);

        MongoClient client = new MongoClient();
        MongoCollection<Document> coll = client.getDatabase("archive").getCollection("iHeartAwards");
        FindIterable<Document> docs = coll.find().sort(new Document("creationDate", 1));

        SequencePreprocessor preprocessor = new SequencePreprocessor(new SequencePreprocessor.OnPointProcessedListener() {

            private double count = 0;

            @Override
            public void emit(DatePoint dp) {
                datePoints.add(dp);
                nel.addPoint(new Point(++count, dp.getY()));
            }
        });

        int count = 0;

        for(Document doc : docs) {
            Date creationDate = doc.getDate("creationDate");
            DatePoint dp = new DatePoint(creationDate, 1.);
            preprocessor.addDatePoint(dp);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("\"date\",\"y\"");
        for(DatePoint dp : datePoints) {
            String row = "\""+sdf.format(dp.getX())+"\""+","+"\""+dp.getY()+"\"";
            row = row.replace(".",",");
            System.out.println(row);
        }

        System.out.println("\n\n\n\n");

        List<List<Cluster<SequenceClusterizable>>> l = nel.calc();

        System.out.println("Ended");
    }

}
