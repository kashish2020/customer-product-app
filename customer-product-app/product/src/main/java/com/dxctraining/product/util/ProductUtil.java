package com.dxctraining.Product.util;

import org.springframework.stereotype.Component;

import com.dxctraining.Product.document.Product;
import com.dxctraining.Product.dto.ProductDto;

@Component
public class ProductUtil {
	public ProductDto productDto(Product product) {
		ProductDto dto = new ProductDto(product.getId(), product.getName());
		return dto;
	}

}
