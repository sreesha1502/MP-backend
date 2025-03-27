package com.vegobject.springboot_mongodb.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.vegobject.springboot_mongodb.collection.Poles;


@Service
@PropertySource(value = "classpath:application.properties")
public class KafkaProducerService {

    @Value(value="${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Poles> kafkaTemplate;
    
    public KafkaProducerService(KafkaTemplate<String, Poles> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key, Poles msg) {
        ProducerRecord<String,Poles> record = new ProducerRecord<>(topicName,key,msg);
        kafkaTemplate.send(record);
    }
}
