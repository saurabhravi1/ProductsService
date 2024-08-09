package com.estore.ProductsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estore.ProductsService.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
	
	ProductEntity findByProductId(String productId);
	
	
}
