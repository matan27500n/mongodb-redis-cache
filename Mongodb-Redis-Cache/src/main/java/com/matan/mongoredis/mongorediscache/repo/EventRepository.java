package com.matan.mongoredis.mongorediscache.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matan.mongoredis.mongorediscache.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, Integer> {

}
