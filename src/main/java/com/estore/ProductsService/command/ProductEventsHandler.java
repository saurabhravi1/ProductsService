package com.estore.ProductsService.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.estore.ProductsService.entity.ProductEntity;
import com.estore.ProductsService.events.ProductCreatedEvent;
import com.estore.ProductsService.repository.ProductRepository;
import com.estore.core.event.ProductReservationCancelledEvent;
import com.estore.core.event.ProductReservedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

	private static final Logger logger = LoggerFactory.getLogger(ProductEventsHandler.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * This is not requried in saga, it is just to study the events
	 * provided by Spring.
	 * 
	 * Don't confuse Mr. Saurabh
	 */
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@EventHandler
	@Transactional
	public void on(ProductCreatedEvent event) throws Exception{

		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		productRepository.save(productEntity);
		applicationEventPublisher.publishEvent(new com.estore.ProductsService.springevents.ProductCreatedEvent(this, productEntity.getTitle()+productEntity.getProductId()));
		logger.info("Spring ProductCreatedEvent published successfully");
	}
	
	@EventHandler
	public void on(ProductReservedEvent event) throws Exception{
		ProductEntity storedProduct = productRepository.findByProductId(event.getProductId());
		storedProduct.setQuantity(storedProduct.getQuantity()-event.getQuantity());
		productRepository.save(storedProduct);
		logger.info("ProductReservedEvent handled for OrderId:{} and ProductId:{} ",event.getOrderId(),event.getProductId());
	}
	
	@EventHandler
	public void on(ProductReservationCancelledEvent event) throws Exception{
		logger.info("ProductReservationCancelledEvent handle start. event:{}",event);
		ProductEntity productEntity = productRepository.findByProductId(event.getProductId());
		int newQuantity = productEntity.getQuantity()+event.getQuantity();
		productEntity.setQuantity(newQuantity);
		productRepository.save(productEntity);
		logger.info("ProductReservationCancelledEvent handle end. Updated productEntity:{}, NewQuantity:{}"
				,productEntity,newQuantity);
		
	}

	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception ex) throws Exception {
		System.out.println("Exception handled in axon handler");
		throw ex;
	}

	@ExceptionHandler(resultType = IllegalArgumentException.class)
	public void handle(IllegalArgumentException ex) throws IllegalArgumentException {
		System.out.println("IllegalArgumentException Exception handled in axon handler");
		throw ex;

	}

}
