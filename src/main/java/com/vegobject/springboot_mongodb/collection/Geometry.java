package com.vegobject.springboot_mongodb.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Geometry {

    private String type;
    private double[] coordinates;

}
