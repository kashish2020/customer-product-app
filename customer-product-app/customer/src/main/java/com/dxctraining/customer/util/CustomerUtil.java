package com.dxctraining.customer.util;

import org.springframework.stereotype.Component;

import com.dxctraining.customermgt.dto.CustomerDto;
import com.dxctraining.customermgt.entities.Customer;

@Component
public class CustomerUtil {

	public CustomerDto customerDto(Customer customer){
		CustomerDto dto = new CustomerDto(customer.getId(),customer.getName());
		return dto;
	}
}
