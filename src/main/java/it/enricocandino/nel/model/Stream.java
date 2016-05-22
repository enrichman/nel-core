package it.enricocandino.nel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Stream {

    private List<Point> data;

    public Stream() {
        this.data = new ArrayList<>();
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
    }

    public void clear() {
        this.data.clear();
    }
}
