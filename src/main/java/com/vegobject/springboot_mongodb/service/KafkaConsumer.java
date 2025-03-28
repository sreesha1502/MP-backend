package com.vegobject.springboot_mongodb.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.vegobject.springboot_mongodb.collection.Poles;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Component
@Service
@Slf4j
@EnableAsync
@PropertySource(value = "classpath:application.properties")
public class KafkaConsumer {

    private MongoClient mongoClient;
    private MongoDatabase database;

    @Value("kafkaMsg")
    private String collectionName; // used @Value to read from app props file

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    KafkaConsumer(@Value("${spring.data.mongodb.uri}") String mongoUri,
            @Value("${spring.data.mongodb.database}") String databaseName) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        this.mongoClient = MongoClients.create(mongoUri);
        this.database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

    }

    // @KafkaListener(topics = "${spring.kafka.topic.name}", groupId =
    // "${spring.kafka.consumer.group-id}")
    // public void consume(String message) {
    // LOGGER.info(String.format("$$ -> Consumed Message -> %s", message));

    // try {
    // MongoCollection<Document> collection =
    // database.getCollection(collectionName);
    // Document doc = new Document("message", message);
    // InsertOneResult result = collection.insertOne(doc);

    // if (result.wasAcknowledged()) {
    // LOGGER.info("*** Message saved **** ");
    // }else{
    // LOGGER.warn("*** Unable to save the message **** ");
    // }
    // } catch(Exception e){
    // LOGGER.error("Error while consuming message", e.getCause());
    // }
    // }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Poles data) {
        LOGGER.info(String.format("$$ -> Consumed Message -> %s", data));

        try {
            MongoCollection<Poles> collection = database.getCollection(collectionName, Poles.class);
            InsertOneResult result = collection.insertOne(data);

            if (result.wasAcknowledged()) {
                LOGGER.info("*** Message saved **** ");
            } else {
                LOGGER.warn("*** Unable to save the message **** ");
            }
        } catch (Exception e) {
            LOGGER.error("Error while consuming message", e.getCause());
        }
    }
}
