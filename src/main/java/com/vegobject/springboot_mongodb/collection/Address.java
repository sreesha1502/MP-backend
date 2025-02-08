package com.vegobject.springboot_mongodb.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String address1;
    private String city;
    private String country;
    private Integer pincode;
}
