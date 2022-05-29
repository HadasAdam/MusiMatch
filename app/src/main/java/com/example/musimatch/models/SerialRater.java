package com.example.musimatch.models;

import com.example.musimatch.models.enums.MelodyRateSections;
import com.example.musimatch.models.enums.PoemRateSections;

import java.io.Serializable;

public class SerialRater implements Serializable {
    Long id;
    PoemRateSections poemRateSections;
    MelodyRateSections melodyRateSections;
    Integer value;
    User userWhoRated;

    public SerialRater() {}

    public SerialRater(Long id, PoemRateSections poemRateSections, MelodyRateSections melodyRateSections, Integer value, User userWhoRated)
    {
        this.id = id;
        this.poemRateSections = poemRateSections;
        this.melodyRateSections = melodyRateSections;
        this.value = value;
        this.userWhoRated = userWhoRated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PoemRateSections getPoemRateSections() {
        return poemRateSections;
    }

    public void setPoemRateSections(PoemRateSections poemRateSections) {
        this.poemRateSections = poemRateSections;
        this.melodyRateSections = null;
    }

    public MelodyRateSections getMelodyRateSections() {
        return melodyRateSections;
    }

    public void setMelodyRateSections(MelodyRateSections melodyRateSections) {
        this.melodyRateSections = melodyRateSections;
        this.poemRateSections = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public User getUserWhoRated()
    {
        return userWhoRated;
    }

    public void setUserWhoRated(User userWhoRated) {
        this.userWhoRated = userWhoRated;
    }
}
