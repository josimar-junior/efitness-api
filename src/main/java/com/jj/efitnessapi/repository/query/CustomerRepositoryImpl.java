package com.jj.efitnessapi.repository.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.jj.efitnessapi.model.Customer;
import com.jj.efitnessapi.repository.filter.CustomerFilter;

public class CustomerRepositoryImpl implements CustomerRepositoryQuery {

	@Autowired
	private EntityManager manager;
	
	@Override
	public Page<Customer> filter(CustomerFilter filter, Pageable pageable) {
		
		StringBuilder jpql = new StringBuilder("from Customer c");
		
		if(hasFilter(filter)) {
			jpql.append("where ").append(createRestrictions(filter));
		}
		
		jpql.append(" order by c.name");
		
		
		
		TypedQuery<Customer> query = manager.createQuery(jpql.toString(), Customer.class);
		
		if(!StringUtils.isEmpty(filter.getName())) {
			query.setParameter("name", "%" + filter.getName() + "%");
		}
		
		if(!StringUtils.isEmpty(filter.getCpf())) {
			query.setParameter("cpf", filter.getCpf());
		}
		
		addRestrictionsPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	private boolean hasFilter(CustomerFilter filter) {
		return filter.getName() != null || filter.getName() != null;
	}

	private Long total(CustomerFilter filter) {
		StringBuilder jpql = new StringBuilder("select count(c) from Customer c");
		
		if(hasFilter(filter)) {
			jpql.append("where ").append(createRestrictions(filter));
		}
		
		return manager.createQuery(jpql.toString(), Long.class).getSingleResult();
	}
	
	private void addRestrictionsPagination(TypedQuery<Customer> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordPage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordPage);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private String createRestrictions(CustomerFilter filter) {
		StringBuilder jpql = new StringBuilder();
		
		if(!StringUtils.isEmpty(filter.getName())) {
			jpql.append("c.name like :name");
		}
		
		if(!StringUtils.isEmpty(filter.getCpf())) {
			if(!StringUtils.isEmpty(filter.getName()))
				jpql.append("c.cpf = :cpf");
			else
				jpql.append("and c.cpf = :cpf");
		}
		
		return jpql.toString();
	}
}
