import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import it.enricocandino.nel.Nel;
import it.enricocandino.nel.clustering.Cluster;
import it.enricocandino.nel.model.DatePoint;
import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.SequenceClusterizable;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Enrico Candino on 25/05/16.
 */
public class MongoStreamReader {

    public static void main(String[] args) throws Exception {
        Nel nel = new Nel(200, 150);

        Calendar cal = Calendar.getInstance();
        cal.set(2016, Calendar.MARCH, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startingDate = cal.getTime();

        Calendar calEnd = Calendar.getInstance();
        calEnd.set(2016, Calendar.APRIL, 1, 0, 0, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        calEnd.add(Calendar.MILLISECOND, -1);
        Date endingDate = calEnd.getTime();

        Map<Date, DatePoint> pointsMap = new HashMap<>();
        while(startingDate.before(endingDate)) {
            pointsMap.put(startingDate, new DatePoint(startingDate, 0.));
            cal.add(Calendar.HOUR, 1);
            startingDate = cal.getTime();
        }

        MongoClient client = new MongoClient();
        MongoCollection<Document> coll = client.getDatabase("archive").getCollection("iHeartAwards");
        FindIterable<Document> docs = coll.find().sort(new Document("creationDate", 1));

        Date last = null;
        for(Document doc : docs) {
            Date creationDate = doc.getDate("creationDate");
            Calendar creationDateCal = Calendar.getInstance();
            creationDateCal.setTime(creationDate);
            creationDateCal.set(Calendar.MINUTE, 0);
            creationDateCal.set(Calendar.SECOND, 0);
            creationDateCal.set(Calendar.MILLISECOND, 0);

            Date hourDay = creationDateCal.getTime();

            DatePoint dp = pointsMap.get(hourDay);
            if(dp != null) {
                dp.setY((dp.getY() + 1));
            }

            if(last == null) {
                last = hourDay;
            } else if(!last.equals(hourDay)) {
                System.out.println("send "+last+" - Y: "+pointsMap.get(last).getY());
                last = hourDay;
            }
        }

        System.out.println("Ended");
    }

}
