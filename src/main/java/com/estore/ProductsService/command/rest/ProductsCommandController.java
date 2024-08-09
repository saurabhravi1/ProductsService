package com.estore.ProductsService.command.rest;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.ProductsService.command.CreateProductCommand;
import com.estore.ProductsService.command.CreateProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

	@Autowired
	private Environment environment;

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping
	public String postProduct(@RequestBody CreateProductRestModel createProductRestModel) {
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.quantity(createProductRestModel.getQuantity()).price(createProductRestModel.getPrice())
				.title(createProductRestModel.getTitle()).productId(UUID.randomUUID().toString()).build();
		String returnValue;
		returnValue = commandGateway.sendAndWait(createProductCommand);
		return returnValue;
	}

}
