package com.jj.efitnessapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jj.efitnessapi.event.ResourceCreatedEvent;
import com.jj.efitnessapi.model.Customer;
import com.jj.efitnessapi.repository.filter.CustomerFilter;
import com.jj.efitnessapi.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<Page<Customer>> filter(CustomerFilter filter, Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.filter(filter, pageable));
	}
	
	@PostMapping
	public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		Customer customerCreated = customerService.save(customer);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, customerCreated.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		return customer == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		customerService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
		Customer customerUpdated = customerService.update(id, customer);
		return ResponseEntity.ok(customerUpdated);
	}
}
