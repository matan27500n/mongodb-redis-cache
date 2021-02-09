package com.matan.mongoredis.mongorediscache.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.matan.mongoredis.mongorediscache.model.Event;

public interface EventRepository extends MongoRepository<Event, Integer> {

}
