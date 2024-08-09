package com.estore.ProductsService.command;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateProductRestModel {
	private String title;
	private BigDecimal price;
	private int quantity;
}
