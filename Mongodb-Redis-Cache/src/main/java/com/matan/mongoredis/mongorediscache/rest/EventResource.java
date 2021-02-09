package com.matan.mongoredis.mongorediscache.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matan.mongoredis.mongorediscache.model.Event;
import com.matan.mongoredis.mongorediscache.repo.EventRepository;

@RestController
@RequestMapping("/mongred")
public class EventResource {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EventRepository eventRepository;

	@PostMapping("addEvent")
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}

	@GetMapping("getOneEvent/{reporterId}")
	public Event getOneEvent(@PathVariable int reporterId) {
		Event event = eventRepository.findById(reporterId).get();
		return event;
	}

	@GetMapping("updateByMetricId/{metricId}/{reporterId}")
	@CachePut(value = "event", key = "#metricId")
	public Event updateByMetricId(@PathVariable(value = "metricId") int metricId,
			@PathVariable(value = "reporterId") int reporterId) {

		Query query = new Query(Criteria.where("metricId").is(metricId));
		Update update = new Update().set("reporterId", reporterId);
		Event event = mongoTemplate.findAndModify(query, update,
				new FindAndModifyOptions().returnNew(true).upsert(false), Event.class);
		return event;

	}

}
