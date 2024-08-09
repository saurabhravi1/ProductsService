package com.estore.ProductsService.springevents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class ProductCreatedEvent extends ApplicationEvent{
	Logger logger  = LoggerFactory.getLogger(ProductCreatedEvent.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -5300245504539586788L;
	private String details;
	
	public ProductCreatedEvent(Object source,String username) {
		super(source);
		this.details=username;
		logger.info("Spring ProductCreatedEvent created successfully.");
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	
	
}
