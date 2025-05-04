package com.vegobject.springboot_mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.LocationRequest;
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
    @RequestMapping(value = "/{cdate}", method = RequestMethod.GET)
    public Poles[] getPolesByDate(@PathVariable long cdate) {
        return polesService.getPolesByDate(cdate);
    }

    @GetMapping
    @RequestMapping(value = "/capturedDates", method = RequestMethod.GET)
    public CapturedDates getCapturedDateStrings() {
        return polesService.getCapturedDateStrings();
    }

    @RequestMapping(value = "/near", method = RequestMethod.POST, consumes = "application/json")
    public Poles[] getPolesNearController(@RequestBody LocationRequest req) {
        System.out.println("Received request: " + req);
        try {
            // Validate the input data
            if (req.getLatitude() == 0.0 && req.getLongitude() == 0.0) {
                throw new IllegalArgumentException("Latitude and Longitude must not be zero");
            }
            if (req.getCapturedDate() == null || req.getCapturedDate().isEmpty()) {
                throw new IllegalArgumentException("Captured date must not be null or empty");
            }
            if (req.getCapturedDate().length() != 10) {
                throw new IllegalArgumentException("Captured date must be in the format YYYY-MM-DD");
            }
            if (req.getCapturedDate().charAt(4) != '-' || req.getCapturedDate().charAt(7) != '-') {
                throw new IllegalArgumentException("Captured date must be in the format YYYY-MM-DD");
            }
            Poles[] poles = polesService.getPolesNear(req.getCapturedDate(), req.getLongitude(), req.getLatitude());
            if (poles == null || poles.length == 0) {
                System.out.println("No poles found near the specified location.");
            } else {
                System.out.println("Poles found near the specified location: " + poles.length);
            }
            return poles;

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return new Poles[0]; // Return an empty array or handle the error as needed
        }
    }

}
