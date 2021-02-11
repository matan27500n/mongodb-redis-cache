package com.matan.mongoredis.mongorediscache.repo;

import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.matan.mongoredis.mongorediscache.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;

//@Repository
public class EventRepo {

	public static final String HASH_KEY = "Event";

	private RedisTemplate<Integer, Event> template;

	private HashOperations hashOperations;

	public EventRepo(RedisTemplate<Integer, Event> redisTemplate) {
		this.template = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	public EventRepo() {
		// TODO Auto-generated constructor stub
	}

	public void saveEvent(Event event) {
		hashOperations.put("USER", event.getReporterId(), event);
	}

	public Map<Integer, Event> findAll() {
		return hashOperations.entries(HASH_KEY);
	}

	public void updateEvent(Event event) {
		saveEvent(event);
	}

	public void deleteProduct(int reporterId) {
		hashOperations.delete(HASH_KEY, reporterId);
	}

	public Event findEventById(int reporterId) {
		return (Event) hashOperations.get(HASH_KEY, reporterId);
	}
}
