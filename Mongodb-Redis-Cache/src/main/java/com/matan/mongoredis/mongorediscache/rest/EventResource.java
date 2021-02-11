package com.matan.mongoredis.mongorediscache.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matan.mongoredis.mongorediscache.model.Event;
import com.matan.mongoredis.mongorediscache.service.EventService;

@RestController
@RequestMapping("/mongred")
public class EventResource {

	@Autowired
	private EventService eventService;

	@PostMapping("addEvent")
	public Event addEvent(@RequestBody Event event) {
		return eventService.addEvent(event);
	}

	@GetMapping("getOneEvent/{reporterId}")
	@Cacheable(value = "event", key = "#reporterId", condition = "#reporterId > 0")
	public Event getOneEvent(@PathVariable int reporterId) {
		return eventService.getOneEvent(reporterId);
	}

	@GetMapping("getAllEvents")
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@PutMapping("updateEvent")
	@CachePut(value = "event", key = "#reporterId")
	public Event updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@DeleteMapping("deleteEvent/{reporterId}")
	@CacheEvict(value = "event", key = "#reporterId")
	public String deleteEvent(@PathVariable int reporterId) {
		eventService.deleteEvent(reporterId);
		return "deleted event successfully";
	}

}
