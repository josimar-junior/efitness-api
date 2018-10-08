package com.jj.efitnessapi.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jj.efitnessapi.model.Customer;
import com.jj.efitnessapi.repository.filter.CustomerFilter;

public interface CustomerRepositoryQuery {

	Page<Customer> filter(CustomerFilter filter, Pageable pageable);
}
