package com.vegobject.springboot_mongodb.collection;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vegobject.springboot_mongodb.serializer.GeometryDeserializer;
import com.vegobject.springboot_mongodb.serializer.GeometrySerializer;

import org.locationtech.jts.geom.Geometry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "poles")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor 
public class Poles {

    @Id
    private String id;
    private double altitude;
    private int speed;
    private int fixType;
    private double courseOverGround;
    private double hdop;
    private String capturedDate;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry gps;


}
