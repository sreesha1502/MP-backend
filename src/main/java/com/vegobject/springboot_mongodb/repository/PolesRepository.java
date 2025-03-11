package com.vegobject.springboot_mongodb.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.vegobject.springboot_mongodb.collection.Poles;
import org.springframework.stereotype.Repository;

@Repository
public interface PolesRepository extends MongoRepository<Poles,String>{

}
