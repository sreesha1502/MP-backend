package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.repository.PolesRepository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.NearQuery;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PolesServiceImpl implements PolesService {

    @Autowired
    private PolesRepository polesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Poles[] getPoles() {
        return polesRepository.findAll().toArray(Poles[]::new);
    }

    public Poles[] getPolesByDate(long cdate) {
        Timestamp ts = new Timestamp(cdate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String captureddate = formatter.format(ts);
        return polesRepository.findAllByCapturedDate(captureddate).toArray(Poles[]::new);
    }

    public CapturedDates getCapturedDateStrings() {
        return polesRepository.findAllCapturedDate();
    }

    public Poles[] getPolesNear(String capturedData, double longitude, double latitude) {
        GeoJsonPoint location = new GeoJsonPoint(longitude, latitude);
        NearQuery nearQuery = NearQuery.near(location).spherical(true).maxDistance(1);
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.geoNear(nearQuery, "distance")
        );
        AggregationResults<Poles> results = mongoTemplate.aggregate(aggregation, "kafka", Poles.class);
        List<Poles> nearbyPoles = results.getMappedResults();
        if (nearbyPoles.isEmpty()) {
            throw new RuntimeException("No nearby poles found.");// Return an empty array if no poles are found
        }
        return nearbyPoles.toArray(Poles[]::new);
    }

}
