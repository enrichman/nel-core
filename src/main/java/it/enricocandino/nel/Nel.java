package it.enricocandino.nel;

import it.enricocandino.nel.model.Point;
import it.enricocandino.nel.model.Sequence;
import it.enricocandino.nel.model.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 *
 * Distributed under the MIT License.
 */
public class Nel {

    private Stream stream;
    private int length;

    private List<Sequence> subsequences = new ArrayList<>();

    public Nel(int length) {
        this.stream = new Stream();
        this.length = length;
    }

    public List<Sequence> getSubsequences() {
        return subsequences;
    }

    public void addPoint(Point point) {
        stream.getData().add(point);

        if(stream.getData().size() >= length) {
            Sequence sequence = new Sequence();
            sequence.setPoints(new ArrayList<>(stream.getData()));
            subsequences.add(sequence);
            stream.clear();
        }
    }

}
