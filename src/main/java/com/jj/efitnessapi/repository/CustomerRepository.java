package com.jj.efitnessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jj.efitnessapi.model.Customer;
import com.jj.efitnessapi.repository.query.CustomerRepositoryQuery;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryQuery {

}
