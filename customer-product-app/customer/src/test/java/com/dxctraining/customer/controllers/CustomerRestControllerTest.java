package com.dxctraining.customermgt.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.customermgt.dto.CustomerDto;
import com.dxctraining.customermgt.entities.Customer;
import com.dxctraining.customermgt.exceptions.CustomerNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({ CustomerRestController.class })
@Transactional
public class CustomerRestControllerTest {

	@Autowired
	private CustomerRestController controller;

	@Autowired
	private EntityManager em;

	@Test
	public void testGetCustomerById_1() {
		Executable execute = () -> controller.findCustomerById(0);
		Assertions.assertThrows(CustomerNotFoundException.class, execute);
	}

	@Test
	public void testGetCustomerById_2() {
		Customer customer = new Customer("kashish");
		customer = em.merge(customer);
		Integer id = customer.getId();
		CustomerDto result = controller.findCustomerById(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(), result.getName());

		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> list = query.getResultList();
		Customer storedCustomer = list.get(0);
		Assertions.assertEquals(customer.getName(), storedCustomer.getName());
		Assertions.assertEquals(result.getId(), storedCustomer.getId());

	}

}
