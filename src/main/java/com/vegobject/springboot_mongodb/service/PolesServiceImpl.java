package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.repository.PolesRepository;


@Service
public class PolesServiceImpl  implements PolesService{

    @Autowired
    private PolesRepository polesRepository;

    public Poles[] getPoles() {
        return  polesRepository.findAll().toArray(Poles[]::new);
    }

}
