package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.Person;
import com.vegobject.springboot_mongodb.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private  PersonRepository personRepository; 

    public String savePerson(Person person) {
        return  "Person saved " + personRepository.save(person).toString() + "saved";
    }

    public String getPerson() {
        return "Person fetched " + personRepository.findAll().toString();
    }
}
