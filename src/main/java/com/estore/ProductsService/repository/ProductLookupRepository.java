package com.estore.ProductsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estore.ProductsService.entity.ProductLookupEntity;

@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String>{
	
	ProductLookupEntity findByProductIdOrTitle(String productId,String title);
}
