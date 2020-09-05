package com.dxctraining.product.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxctraining.Product.document.Product;

public interface IProductDao extends MongoRepository<Product, String> {
	List<Product> findByName(String name);
}
