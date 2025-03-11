package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.repository.PolesRepository;


@Service
public class PolesServiceImpl  implements PolesService{

    @Autowired
    private PolesRepository polesRepository;

    public String getPoles() {
        return "Poles fetched " + polesRepository.findAll().toString();
    }

}
