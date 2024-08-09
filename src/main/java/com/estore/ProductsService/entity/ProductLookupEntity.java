package com.estore.ProductsService.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PRODUCT_LOOKUP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLookupEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369363498072807475L;
	
	@Id
	private String productId;
	private String title;
}
