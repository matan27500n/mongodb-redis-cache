package com.matan.mongoredis.mongorediscache.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int reporterId;
	private Date timestamp;
	@Indexed
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
