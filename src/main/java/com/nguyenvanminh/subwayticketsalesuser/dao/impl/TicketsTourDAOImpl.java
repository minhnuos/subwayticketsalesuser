package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;

@Transactional
@Repository
public class TicketsTourDAOImpl implements TicketsTourDAO{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TicketsTour> listTicketsTours() {
		// TODO Auto-generated method stub
		String jql = "SELECT t FROM TicketsTour t";
		List<TicketsTour> ticketsTours = this.entityManager.createQuery(jql,TicketsTour.class).getResultList();
		return ticketsTours;
	}
}
