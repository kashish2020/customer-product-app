package com.dxctraining.wishlist.controllers;

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
import org.springframework.web.client.RestTemplate;

import com.dxctraining.wishlist.dto.CreateWishedItemRequest;
import com.dxctraining.wishlist.dto.CustomerDto;
import com.dxctraining.wishlist.dto.ProductDto;
import com.dxctraining.wishlist.dto.WishedItemDto;
import com.dxctraining.wishlist.entities.WishedItem;
import com.dxctraining.wishlist.services.IWishedItemService;
import com.dxctraining.wishlist.util.WishedItemUtil;

@RestController
@RequestMapping("wisheditems")
public class WishedItemRestController {

	@Autowired
	private WishedItemUtil util;

	@Autowired
	private IWishedItemService service;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public WishedItemDto addItem(@RequestBody CreateWishedItemRequest request) {
		Integer custId = request.getCustomerId();
		String prodId = request.getProductId();
		WishedItem wishedItem = new WishedItem(custId, prodId);
		wishedItem = service.add(wishedItem);
		CustomerDto customerDto = findCustomerDetailsByCustomerId(request.getCustomerId());
		customerDto.setCustomerId(request.getCustomerId());
		ProductDto productDto = findProductDetailsByProductId(request.getProductId());
		WishedItemDto response = util.wishedItemDto(wishedItem, custId, prodId, customerDto.getName(),
				productDto.getName());
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WishedItemDto findAllWishedItemsById(@PathVariable("id") String id) {
		WishedItem wishedItem = service.findById(id);
		String productId = wishedItem.getProductId();
		Integer customerId = wishedItem.getCustomerId();
		ProductDto productDto = findProductDetailsByProductId(productId);
		CustomerDto customerDto = findCustomerDetailsByCustomerId(customerId);
		WishedItemDto response = util.wishedItemDto(wishedItem, customerId, productDto.getName(), productId,
				customerDto.getName());
		return response;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<WishedItemDto> fetchAllWishedIem() {
		List<WishedItem> list = service.allWishedItems();
		List<WishedItemDto> response = new ArrayList<>();
		for (WishedItem wishedItem : list) {
			String productId = wishedItem.getProductId();
			ProductDto productDto = findProductDetailsByProductId(productId);
			Integer custId = wishedItem.getCustomerId();
			CustomerDto customerDto = findCustomerDetailsByCustomerId(custId);
			WishedItemDto dto = util.wishedItemDto(wishedItem, custId, customerDto.getName(), productId,
					productDto.getName());
			response.add(dto);
		}
		return response;
	}

	public CustomerDto findCustomerDetailsByCustomerId(Integer custId) {
		String url = "http://localhost:8787/customers/get/" + custId;
		CustomerDto dto = restTemplate.getForObject(url, CustomerDto.class);
		return dto;
	}

	public ProductDto findProductDetailsByProductId(String productId) {
		String url = "http://localhost:8786/products/get/" + productId;
		ProductDto dto = restTemplate.getForObject(url, ProductDto.class);
		return dto;
	}

}
