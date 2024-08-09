package com.estore.ProductsService.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estore.ProductsService.entity.ProductEntity;
import com.estore.ProductsService.query.rest.ProductRestModel;
import com.estore.ProductsService.repository.ProductRepository;

@Component
public class ProductsQueryHandler {
	
	@Autowired
	private ProductRepository productRepository;
	
	@QueryHandler
	public List<ProductRestModel> findProducts(FindProductsQuery query){
		
		List<ProductRestModel> productList = new ArrayList<>();
		List<ProductEntity> storedProducts = productRepository.findAll();
		storedProducts.forEach(storedProduct->{
			ProductRestModel p = new ProductRestModel();
			BeanUtils.copyProperties(storedProduct, p);
			productList.add(p);
		});
		return productList;
	}
	
}
