package it.enricocandino.nel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Stream<T extends Point> {

    private List<T> data;

    public Stream() {
        this.data = new ArrayList<T>();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
