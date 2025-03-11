package com.vegobject.springboot_mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vegobject.springboot_mongodb.service.PolesService;

@RestController
@RequestMapping("/poles")
public class PolesController {

    @Autowired
    private PolesService polesService;

    @GetMapping
    public String getPoles() {
        return polesService.getPoles();
    }
}
