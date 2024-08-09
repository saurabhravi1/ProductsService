package com.estore.ProductsService.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estore.ProductsService.entity.ProductLookupEntity;
import com.estore.ProductsService.events.ProductCreatedEvent;
import com.estore.ProductsService.repository.ProductLookupRepository;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
	
	@Autowired
	private ProductLookupRepository productLookupRepository;
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductLookupEntity lookupEntity = 
				new ProductLookupEntity(event.getProductId(), event.getTitle());
		productLookupRepository.save(lookupEntity);
	}

}
