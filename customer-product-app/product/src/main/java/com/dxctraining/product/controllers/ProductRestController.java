package com.dxctraining.product.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.Product.document.Product;
import com.dxctraining.Product.dto.CreateProductRequest;
import com.dxctraining.Product.dto.ProductDto;
import com.dxctraining.Product.services.IProductService;
import com.dxctraining.Product.util.ProductUtil;

@RequestMapping("/products")
@RestController
public class ProductRestController {

	@Autowired
	private IProductService service;

	@Autowired
	private ProductUtil util;

	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto save(@RequestBody CreateProductRequest requestData) {
		Product product = new Product(requestData.getName());
		product = service.save(product);
		ProductDto resp = util.productDto(product);
		return resp;
	}

	@GetMapping("/get/{id}")
	public ProductDto findProduct(@PathVariable("id") String id) {
		Product product = service.findById(id);
		ProductDto response = util.productDto(product);
		return response;
	}

	@GetMapping("/get/product/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchProductByName(@PathVariable("name") String name) {
		List<Product> list = service.findProductByName(name);
		List<ProductDto> response = new ArrayList<>();
		for (Product product : list) {
			ProductDto productDto = util.productDto(product);
			response.add(productDto);
		}
		return response;

	}

	@GetMapping("/allproducts")
	public List<ProductDto> fetchAll() {
		List<Product> list = service.findAll();
		List<ProductDto> response = new ArrayList<>();
		for (Product product : list) {
			ProductDto dto = util.productDto(product);
			response.add(dto);
		}
		return response;

	}
}
