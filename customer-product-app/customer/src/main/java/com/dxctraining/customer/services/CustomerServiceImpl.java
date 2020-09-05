package com.dxctraining.customer.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.customermgt.dao.ICustomerDao;
import com.dxctraining.customermgt.entities.Customer;
import com.dxctraining.customermgt.exceptions.CustomerNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao dao;

	@Override
	public Customer save(Customer customer) {
		customer= dao.save(customer);
		return customer;
	}

	@Override
	public Customer findById(Integer id) {
		validate(id);
		Optional<Customer> optional = dao.findById(id);
		boolean exist = optional.isPresent();
		if (!exist) {
		}
		Customer customer = optional.get();
		return customer;
	}

	@Override
	public void remove(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Customer> findByName(String name) {
		validate(name);
		List<Customer> list = dao.findByName(name);
		return list;
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> list = dao.findAll();
		return list;
	}

	private void validate(Object obj) {
		if (obj == null) {
			throw new CustomerNotFoundException("Customer not found:" + obj);
		}
	}

}
