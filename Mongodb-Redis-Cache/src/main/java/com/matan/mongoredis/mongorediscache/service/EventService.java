package com.matan.mongoredis.mongorediscache.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matan.mongoredis.mongorediscache.model.Event;
import com.matan.mongoredis.mongorediscache.repo.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}

	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}

	public void deleteEvent(int reporterId) {
		eventRepository.deleteById(reporterId);
	}

	public Event getOneEvent(int reporterId) {
		return eventRepository.findById(reporterId).get();
	}

	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

}
