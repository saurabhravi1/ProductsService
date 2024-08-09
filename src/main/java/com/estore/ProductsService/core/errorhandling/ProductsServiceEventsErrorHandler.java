package com.estore.ProductsService.core.errorhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.stereotype.Component;

public class ProductsServiceEventsErrorHandler 
	implements ListenerInvocationErrorHandler {

	@Override
	public void onError(Exception exception, EventMessage<?> event, 
			EventMessageHandler eventHandler) throws Exception {
		throw exception;
	}

}
