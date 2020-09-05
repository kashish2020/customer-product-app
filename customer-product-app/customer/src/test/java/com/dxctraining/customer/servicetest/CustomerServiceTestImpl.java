package com.dxctraining.customermgt.servicetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.customermgt.entities.Customer;
import com.dxctraining.customermgt.exceptions.CustomerNotFoundException;
import com.dxctraining.customermgt.exceptions.InvalidArgumentException;
import com.dxctraining.customermgt.services.CustomerServiceImpl;
import com.dxctraining.customermgt.services.ICustomerService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(CustomerServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerServiceImplTest {

	@Autowired
	private ICustomerService service;

	@Test
	public void testAdd_2() {
		String name = "kashish";
		Customer customer = new Customer(name);
		customer = service.save(customer);
		Customer fetched = service.findById(customer.getId());
		Assertions.assertEquals(customer.getId(), fetched.getId());
		Assertions.assertEquals(name, fetched.getName());
	}

	@Test
	public void testFindById_1() {
		Executable executable = () -> service.findById(null);
		Assertions.assertThrows(CustomerNotFoundException.class, executable);
	}

	@Test
	public void testFindById_3() {
		String name = "singh";
		Customer customer = new Customer(name);
		customer = service.save(customer);
		Customer fetched = service.findById(customer.getId());
		Assertions.assertEquals(customer.getId(), fetched.getId());
	}

}