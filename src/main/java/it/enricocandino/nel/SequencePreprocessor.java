package it.enricocandino.nel;

import it.enricocandino.nel.model.DatePoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Enrico Candino on 02/06/16.
 */
public class SequencePreprocessor {

    private DatePoint lastPoint;
    private OnPointProcessedListener onPointProcessedListener;

    public SequencePreprocessor(OnPointProcessedListener onPointProcessedListener) {
        this.onPointProcessedListener = onPointProcessedListener;
    }

    public void addDatePoint(DatePoint datePoint) {

        Date curr = datePoint.getX();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curr);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // normalized date
        curr = cal.getTime();
        datePoint.setX(curr);

        if (lastPoint == null) {
            lastPoint = datePoint;

        } else {

            if (lastPoint.getX().equals(curr)) {
                lastPoint.setY(lastPoint.getY() + datePoint.getY());

            } else if (onPointProcessedListener != null) {
                onPointProcessedListener.emit(lastPoint);
                lastPoint = null;
            }
        }
    }

    public interface OnPointProcessedListener {
        void emit(DatePoint dp);
    }
}
