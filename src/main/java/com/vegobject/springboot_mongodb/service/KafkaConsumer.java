package com.vegobject.springboot_mongodb.service;

import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import org.springframework.beans.factory.annotation.Value;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaConsumer {

    private MongoClient mongoClient;
    private MongoDatabase database;

    @Value("kafkaMsg")
    private String collectionName; // used @Value to read from app props file

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    KafkaConsumer(@Value("${spring.data.mongodb.uri}") String mongoUri, @Value("${spring.data.mongodb.database}") String databaseName) {
        this.mongoClient = MongoClients.create(mongoUri);
        this.database = mongoClient.getDatabase(databaseName);
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        LOGGER.info(String.format("$$ -> Consumed Message -> %s", message));

          try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document doc = new Document("message", message);
            InsertOneResult result = collection.insertOne(doc);

            if (result.wasAcknowledged()) {
                LOGGER.info("*** Message saved **** ");
            }else{
                LOGGER.warn("*** Unable to save the message **** ");
            }
        } catch(Exception e){
            LOGGER.error("Error while consuming message", e.getCause());
        }
    }
}
