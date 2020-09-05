package com.dxctraining.customer.controllers;

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

import com.dxctraining.customermgt.dto.CreateCustomerRequest;
import com.dxctraining.customermgt.dto.CustomerDto;
import com.dxctraining.customermgt.entities.Customer;
import com.dxctraining.customermgt.services.ICustomerService;
import com.dxctraining.customermgt.util.CustomerUtil;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	@Autowired
	private ICustomerService service;

	@Autowired
	private CustomerUtil util;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto addCustomer(@RequestBody CreateCustomerRequest requestData) {
		Customer customer = new Customer();
		customer.setName(requestData.getName());
		customer = service.save(customer);
		CustomerDto response = util.customerDto(customer);
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDto findCustomerById(@PathVariable("id") Integer id) {
		Customer customer = service.findById(id);
		CustomerDto response = util.customerDto(customer);
		return response;
	}

	@GetMapping("getname/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> findCustomerByName(@PathVariable("name") String name) {
		List<Customer> list = service.findByName(name);
		List<CustomerDto> response = new ArrayList<>();
		for (Customer customer : list) {
			CustomerDto dto = util.customerDto(customer);
			response.add(dto);
		}
		return response;
	}

	@GetMapping("/get/allcustomers")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> findAllCustomer() {
		List<CustomerDto> response = new ArrayList<>();
		List<Customer> list = service.findAll();
		for (Customer customer : list) {
			CustomerDto dto = util.customerDto(customer);
			response.add(dto);
		}
		return response;
	}

}
