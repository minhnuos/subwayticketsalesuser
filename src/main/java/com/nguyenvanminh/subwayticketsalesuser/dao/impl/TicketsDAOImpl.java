package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Tickets;

@Repository
@Transactional
public class TicketsDAOImpl implements TicketsDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updateTickets(Tickets tickets) {
		this.entityManager.merge(tickets);
		
	}

	@Override
	public Tickets findTicketsById(int id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Tickets.class, id);
	}
}
