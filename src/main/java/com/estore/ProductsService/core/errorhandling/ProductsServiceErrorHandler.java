package com.estore.ProductsService.core.errorhandling;

import java.util.Date;

import org.axonframework.commandhandling.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductsServiceErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceErrorHandler.class);
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest req) {
		logger.info("handleIllegalStateException called");
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(Exception ex, WebRequest req) {
		logger.info("handleAllException called");
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(CommandExecutionException.class)
	public ResponseEntity<ErrorMessage> handleCommandExecutionException(CommandExecutionException ex, WebRequest req) {
		logger.info("CommandExecutionException called");
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
