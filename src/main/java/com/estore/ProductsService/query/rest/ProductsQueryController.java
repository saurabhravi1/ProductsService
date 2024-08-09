package com.estore.ProductsService.query.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.ProductsService.query.FindProductsQuery;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

	@Autowired
	private QueryGateway queryGateway;

	@GetMapping
	public List<ProductRestModel> getProduct() {

		FindProductsQuery findProductsQuery = new FindProductsQuery();
		CompletableFuture<List<ProductRestModel>> queryResultFuture = queryGateway.query(findProductsQuery,
				ResponseTypes.multipleInstancesOf(ProductRestModel.class));
		List<ProductRestModel> productList = queryResultFuture.join();

		return productList;
	}
}
