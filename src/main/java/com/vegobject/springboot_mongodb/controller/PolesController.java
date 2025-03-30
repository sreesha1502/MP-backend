package com.vegobject.springboot_mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.service.PolesService;


@RestController
@RequestMapping("/poles")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://dt14.idi.ntnu.no:8080" })
public class PolesController {

    @Autowired
    private PolesService polesService;

    @GetMapping
    public Poles[] getPoles() {
        return polesService.getPoles();
    }

    @GetMapping("/{cdate}")
    @RequestMapping(value = "/{cdate}", method= RequestMethod.GET)
    public Poles[] getPolesByDate(@PathVariable long cdate) {
        return polesService.getPolesByDate(cdate);
    }

    @GetMapping
    @RequestMapping(value = "/capturedDates", method = RequestMethod.GET)
    public CapturedDates getCapturedDateStrings() {
        return polesService.getCapturedDateStrings();
    }
}
