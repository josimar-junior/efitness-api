package com.jj.efitnessapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jj.efitnessapi.model.Customer;
import com.jj.efitnessapi.repository.CustomerRepository;
import com.jj.efitnessapi.repository.filter.CustomerFilter;
import com.jj.efitnessapi.service.exception.ResourceNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	}
	
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	public Page<Customer> filter(CustomerFilter filter, Pageable pageable) {
		return customerRepository.filter(filter, pageable);
	}
	
	public Customer update(Long id, Customer customer) {
		customer.setId(id);
		checkForExistence(customer);
		return customerRepository.save(customer);
	}
	
	private void checkForExistence(Customer customer) {
		findById(customer.getId());
	}
}
