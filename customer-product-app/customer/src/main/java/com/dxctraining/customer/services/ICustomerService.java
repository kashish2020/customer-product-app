package com.dxctraining.customer.services;

import java.util.List;

import com.dxctraining.customermgt.entities.Customer;

public interface ICustomerService {
	
	Customer save(Customer customer);
	
	Customer findById(Integer id);
	
	void remove(Integer id); 
	
	List <Customer> findByName(String name);

	List<Customer> findAll();
	
	 
	}
