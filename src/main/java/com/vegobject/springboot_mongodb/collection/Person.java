package com.vegobject.springboot_mongodb.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document (collection= "person")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Person {
    @Id
    private String personId;
    private String name;
    private String age;
    private Address address;
}
