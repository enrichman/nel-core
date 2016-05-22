package it.enricocandino.nel.clustering.model;

/**
 * Created by Enrico Candino on 21/05/16.
 */
public abstract class Clusterizable<T> {

    public Clusterizable(T value) {
        this.value = value;
    }

    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract double getDistance(Clusterizable<T> clusterizable);

}
