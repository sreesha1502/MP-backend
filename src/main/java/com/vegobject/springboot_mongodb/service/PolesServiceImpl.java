package com.vegobject.springboot_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.repository.PolesRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat; 

@Service
public class PolesServiceImpl implements PolesService {

    @Autowired
    private PolesRepository polesRepository;

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

}
