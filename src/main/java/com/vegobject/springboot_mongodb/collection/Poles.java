package com.vegobject.springboot_mongodb.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "poles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poles {

    @Id
    private String id;
    private double altitude;
    private int speed;
    // private Geometry geometry;


}
