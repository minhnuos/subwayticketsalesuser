package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.CustomerDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Customer;

@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addCustomer(Customer customer) {
		this.entityManager.persist(customer);
		
	}
}
