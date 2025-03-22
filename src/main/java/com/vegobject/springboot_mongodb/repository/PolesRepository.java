package com.vegobject.springboot_mongodb.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;

import org.springframework.stereotype.Repository;

@Repository
public interface PolesRepository extends MongoRepository<Poles,String>{

//     @Aggregation(pipeline = {
//         "{'$match':{'transaction_type':?0, 'price': {$gt: ?1} }}",
//         "{'$sample':{size:?2}}",
//         "{'$sort':{'area':-1}}"
// })

    @Aggregation(pipeline = {
        "{'$group':{ '_id': null, 'capturedDates': {'$addToSet': '$capturedDate'}}}",
        "{'$project': {'_id': 0}}"
    })
    CapturedDates findAllCapturedDate();



}
