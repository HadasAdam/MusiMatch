package com.example.musimatch.models;

public class SerialRater {
    Long id;
    Rater rater;
    Integer value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
