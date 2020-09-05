package com.dxctraining.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.Product.dao.IProductDao;
import com.dxctraining.Product.document.Product;
import com.dxctraining.Product.exceptions.ProductNotFoundException;

@Service
public class PrdouctServiceImpl implements IProductService {

	@Autowired
	private IProductDao dao;

	@Override
	public Product save(Product product) {
		product = dao.save(product);
		return product;
	}

	@Override
	public void remove(String id) {
		validate(id);
		dao.deleteById(id);
	}

	@Override
	public Product findById(String id) {
		validate(id);
		Optional<Product> optional = dao.findById(id);
		boolean exist = optional.isPresent();
		if (!exist) {
			throw new ProductNotFoundException("product not found for id=" + id);
		}
		Product product = optional.get();
		return product;
	}

	public void validate(String id) {
		if (id == null) {
			throw new ProductNotFoundException("Product not found");
		}
	}

	@Override
	public List<Product> findAll() {
		List<Product> list = dao.findAll();
		return list;
	}

	@Override
	public List<Product> findProductByName(String name) {
		List<Product>list = dao.findByName(name);
		return list;
	}

}
