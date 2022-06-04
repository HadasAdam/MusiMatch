package com.example.musimatch.models;

import androidx.room.Entity;

@Entity
public class AverageRater {
    Long id;
    Double raterSection1 = 0D;
    Double raterSection2 = 0D;
    Double raterSection3 = 0D;

    public AverageRater(){}

    public AverageRater(Double raterSection1, Double raterSection2, Double raterSection3)
    {
        this.raterSection1 = raterSection1;
        this.raterSection2 = raterSection2;
        this.raterSection3 = raterSection3;
    }

    //@Override
    public Long getId() {
        return id;
    }

    //@Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getRaterSection1() {
        return raterSection1;
    }

    public void setRaterSection1(Double raterSection1) {
        this.raterSection1 = raterSection1;
    }

    public Double getRaterSection2() {
        return raterSection2;
    }

    public void setRaterSection2(Double raterSection2) {
        this.raterSection2 = raterSection2;
    }

    public Double getRaterSection3() {
        return raterSection3;
    }

    public void setRaterSection3(Double raterSection3) {
        this.raterSection3 = raterSection3;
    }

}
