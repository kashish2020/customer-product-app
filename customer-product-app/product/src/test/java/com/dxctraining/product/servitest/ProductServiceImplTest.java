package com.dxctraining.product.servitest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.Product.document.Product;
import com.dxctraining.Product.exceptions.ProductNotFoundException;
import com.dxctraining.Product.services.IProductService;
import com.dxctraining.Product.services.PrdouctServiceImpl;

@DataMongoTest
@Import({ PrdouctServiceImpl.class })
public class ProductServiceImplTest {

	@Autowired
	private IProductService service;

	@Autowired
	private MongoTemplate mongo;
	
	@Test
	public void testFindById_1() {
		Executable execute = () -> service.findById(null);
		Assertions.assertThrows(ProductNotFoundException.class, execute);
	}


	@Test
	public void testAdd_2() {
		String name = "singh";
		Product customer = new Product(name);
		customer = service.save(customer);
		String id = customer.getId();
		Product fetched = mongo.findById(id, Product.class);
		Assertions.assertEquals(customer.getId(), fetched.getId());
		Assertions.assertEquals(name, fetched.getName());
	}


	@Test
	public void testFindById_3() {
		Product customer = new Product("kashish");
		customer = mongo.save(customer);
		String id = customer.getId();
		Product result = service.findById(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(), result.getName());
	}
}