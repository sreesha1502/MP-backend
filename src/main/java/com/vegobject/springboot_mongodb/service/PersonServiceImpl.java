package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.Person;
import com.vegobject.springboot_mongodb.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private  PersonRepository personRepository; 

    @Override
    public String savePerson(Person person) {
        return  personRepository.save(person).toString();
    }
}
