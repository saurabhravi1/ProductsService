package com.estore.ProductsService.springevents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This is not requried in saga, it is just to study the events
 * provided by Spring.
 * 
 * Don't confuse Mr. Saurabh
 */

@Component
public class ProductCreatedEventListner implements ApplicationListener<ProductCreatedEvent>{
	
	Logger logger = LoggerFactory.getLogger(ProductCreatedEventListner.class);
	
	@Override
	public void onApplicationEvent(ProductCreatedEvent product) {
		logger.info("Spring ProductCreatedEvent handled successfully product:{}",product.getDetails());
	}
	
}
