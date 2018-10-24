package com.jj.efitnessapi.repository.query;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		
		StringBuilder jpql = new StringBuilder("from Customer c where 1=1 ");
		Map<String, Object> parameters = new HashMap<>();
		
		setParametersIfRequired(filter, jpql, parameters);
		
		jpql.append(" order by c.name");
		
		TypedQuery<Customer> query = manager.createQuery(jpql.toString(), Customer.class);
		
		setParametersQuery(parameters, query);
		
		addRestrictionsPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	private void setParametersIfRequired(CustomerFilter filter, StringBuilder jpql, Map<String, Object> parameters) {
		if(StringUtils.hasText(filter.getName())) {
			jpql.append("and c.name like :name ");
			parameters.put("name", "%" + filter.getName() + "%");
		}
		
		if(!StringUtils.isEmpty(filter.getCpf())) {
			jpql.append("and c.cpf = :cpf ");
			parameters.put("cpf", filter.getCpf());
		}
	}
	
	private void setParametersQuery(Map<String, Object> parameters, Query query) {
		parameters.forEach((k, v) -> {
			query.setParameter(k, v);
		});
	}

	private Long total(CustomerFilter filter) {
		StringBuilder jpql = new StringBuilder("select count(c) from Customer c where 1=1 ");
		Map<String, Object> parameters = new HashMap<>();
		
		setParametersIfRequired(filter, jpql, parameters);
		
		TypedQuery<Long> query = manager.createQuery(jpql.toString(), Long.class);
		
		setParametersQuery(parameters, query);
		
		return query.getSingleResult();
	}
	
	private void addRestrictionsPagination(TypedQuery<Customer> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordPage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordPage);
		query.setMaxResults(totalRecordsPerPage);
	}
	
}
