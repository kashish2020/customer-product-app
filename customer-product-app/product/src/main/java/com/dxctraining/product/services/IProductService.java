package com.dxctraining.product.services;

import java.util.List;

import com.dxctraining.Product.document.Product;

public interface IProductService {
	
	Product save(Product product);
	
	void remove (String id);
	
	Product findById(String id);
	
	List<Product> findAll();
	
	List<Product> findProductByName(String name);
}

