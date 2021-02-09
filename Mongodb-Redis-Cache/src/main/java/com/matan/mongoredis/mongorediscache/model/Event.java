package com.matan.mongoredis.mongorediscache.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

	@Id
	private int reporterId;
	private Date timestamp;
	private int metricId;
	private int metricValue;
	private String message;

	public Event(int reporterId, int metricId, int metricValue, String message) {
		this.reporterId = reporterId;
		this.metricId = metricId;
		this.metricValue = metricValue;
		this.message = message;
	}

}
