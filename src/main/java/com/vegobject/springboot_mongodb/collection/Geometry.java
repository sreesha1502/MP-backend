package com.vegobject.springboot_mongodb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor 
public class Geometry {

    private String type;
    private double[] coordinates;

    // public Geometry() {
    // }

    // @JsonCreator
    // public Geometry(@JsonProperty("type") String type, @JsonProperty("coordinates") double[] coordinates) {
    //     this.type = type;
    //     this.coordinates = coordinates;
    // }

    // public String getType() {
    //     return type;
    // }

    // public void setType(String type) {
    //     this.type = type;
    // }


    // public double[] getCoordinates() {
    //     return coordinates;
    // }

    // public void setCoordinates(double[] coordinates) {
    //     this.coordinates = coordinates;
    // }

}
