package com.vegobject.springboot_mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vegobject.springboot_mongodb.collection.Poles;
import com.vegobject.springboot_mongodb.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Poles poles) {
        kafkaProducerService.sendMessage("poles", poles);
        return "Message sent to Kafka topic";
    }

}
