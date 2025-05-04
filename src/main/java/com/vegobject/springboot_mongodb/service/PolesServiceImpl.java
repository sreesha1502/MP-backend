package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.repository.Near;
import org.springframework.stereotype.Service;

import org.springframework.data.geo.Point;
import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.repository.PolesRepository;

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
        Point location = new Point(longitude, latitude);
        NearQuery nearQuery = NearQuery.near(location).spherical(true).maxDistance(1);
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.geoNear(nearQuery,"distance"));
        List<Poles> poles = mongoTemplate.aggregate(aggregation, "KafkaMsg", Poles.class).getMappedResults();
        if (poles == null || poles.isEmpty()) {
            throw new RuntimeException("No nearby poles found.");// Return an empty array if no poles are found
        }
        return poles.toArray(Poles[]::new);
    }

}
