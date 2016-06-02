package it.enricocandino.nel.model;

import java.util.Date;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class DatePoint {

    private String seqId;
    private Date x;
    private Double y;

    public DatePoint(String seqId, Date x, Double y) {
        this.seqId = seqId;
        this.x = x;
        this.y = y;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public Date getX() {
        return x;
    }

    public void setX(Date x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}
