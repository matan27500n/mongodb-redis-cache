package com.matan.mongoredis.mongorediscache.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matan.mongoredis.mongorediscache.model.Event;
import com.matan.mongoredis.mongorediscache.repo.EventRepo;
import com.matan.mongoredis.mongorediscache.repo.EventRepository;

@RestController
@RequestMapping("/mongred")
@CacheConfig(cacheNames = "redisCache")
public class EventResource {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EventRepository eventRepository;

	@PostMapping("addEvent")
	public Event saveEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}

	@GetMapping("getOneEvent/{reporterId}")
	@Cacheable(value = "event", key = "#reporterId", condition = "#reporterId > 0")
	public Event getOneEvent(@PathVariable int reporterId) {
		return eventRepository.findById(reporterId).get();
	}

	@GetMapping("getAllEvents")
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@PutMapping("updateEvent")
	@CachePut(value = "event", key = "#reporterId")
	public Event updateEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}

	@DeleteMapping("deleteEvent/{reporterId}")
	@CacheEvict(value = "event", key = "#reporterId")
	public String deleteEvent(@PathVariable int reporterId) {
		eventRepository.deleteById(reporterId);
		return "deleted event successfully";
	}

//	@PutMapping("updateByMetricId/{metricId}/{metricValue}")
//	@CachePut(value = "event", key = "#metricId")
//	public Event updateByMetricId(@PathVariable(value = "metricId") int metricId,
//			@PathVariable(value = "metricValue") int metricValue) {
//
//		Query query = new Query(Criteria.where("metricId").is(metricId));
//		Update update = new Update().set("metricValue", metricValue);
//		Event result = mongoTemplate.findAndModify(query, update,
//				new FindAndModifyOptions().returnNew(true).upsert(false), Event.class);
//		return result;
//
//	}

}
