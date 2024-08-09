package com.estore.ProductsService.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2767556044685048004L;
	
	@Id
	@Column(unique = true)
	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quantity;

}
