package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDetailDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTourDetail;

@Repository
@Transactional
public class TicketsTourDetailDAOImpl implements TicketsTourDetailDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updateTicketsTourDetail(TicketsTourDetail ticketsTourDetail) {
		this.entityManager.merge(ticketsTourDetail);
		
	}

	@Override
	public TicketsTourDetail findTicketsTourDetailById(int id) {
		
		return this.entityManager.find(TicketsTourDetail.class, id);
	}
}
