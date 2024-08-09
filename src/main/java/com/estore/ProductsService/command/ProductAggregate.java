package com.estore.ProductsService.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.estore.ProductsService.events.ProductCreatedEvent;
import com.estore.core.command.CancelProductReservationCommand;
import com.estore.core.command.ReserveProductCommand;
import com.estore.core.event.ProductReservationCancelledEvent;
import com.estore.core.event.ProductReservedEvent;

import lombok.Data;

@Aggregate
@Data
public class ProductAggregate {
	
	private static final Logger logger  = LoggerFactory.getLogger(ProductAggregate.class);
	
	@AggregateIdentifier
	private String productId;
	private String title;
	private BigDecimal price;
	private int quantity;
	
	public ProductAggregate() {}
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand command){
		if(command.getPrice().compareTo(BigDecimal.ZERO)<=0)
			throw new IllegalArgumentException("Price can not be less than zero");
		
		ProductCreatedEvent createdEvent = new ProductCreatedEvent();
		BeanUtils.copyProperties(command, createdEvent);
		logger.info("createdEvent:"+createdEvent);		
		AggregateLifecycle.apply(createdEvent);		
		
		
	}
	
	@CommandHandler
	public void handle(ReserveProductCommand reserveProductCommand) throws Exception{
		
		if(quantity < reserveProductCommand.getQuantity()) {
			throw new IllegalArgumentException("Insufficient number of items in stock");
		}
		
		ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
				.orderId(reserveProductCommand.getOrderId())
				.productId(reserveProductCommand.getProductId())
				.quantity(reserveProductCommand.getQuantity())
				.userId(reserveProductCommand.getUserId())
				.build();
				
		AggregateLifecycle.apply(productReservedEvent);
	}
	
	@CommandHandler
	public void handle(CancelProductReservationCommand command) {
		ProductReservationCancelledEvent event = ProductReservationCancelledEvent.builder()
				.productId(command.getProductId())
				.orderId(command.getOrderId())
				.quantity(command.getQuantity())
				.userId(command.getUserId())
				.reason(command.getReason())
				.build();
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(ProductReservationCancelledEvent event) {
		this.quantity+=event.getQuantity();
	}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) throws Exception{
		logger.info("productCreatedEvent:"+productCreatedEvent);
		this.productId=productCreatedEvent.getProductId();
		this.quantity = productCreatedEvent.getQuantity();
		this.price = productCreatedEvent.getPrice();
		this.title = productCreatedEvent.getTitle();
		
	}
	
	@EventSourcingHandler
	public void on(ProductReservedEvent event) {
		this.quantity-=event.getQuantity();		
	}
	
}
