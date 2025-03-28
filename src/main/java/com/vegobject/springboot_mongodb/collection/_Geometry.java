package com.vegobject.springboot_mongodb.collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class _Geometry {

    private String type;
    private double[] coordinates;

    public _Geometry() {
    }

    @JsonCreator
    public _Geometry(@JsonProperty("type") String type, @JsonProperty("coordinates") double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

}
