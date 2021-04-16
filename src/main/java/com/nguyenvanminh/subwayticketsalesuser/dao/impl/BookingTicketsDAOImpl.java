package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.BookingTicketsDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.BookingTickets;

@Transactional
@Repository
public class BookingTicketsDAOImpl implements BookingTicketsDAO{

	@PersistenceContext 
	EntityManager entityManager;

	@Override
	public void addBookingTickets(BookingTickets bookingTickets) {
		// TODO Auto-generated method stub
		this.entityManager.persist(bookingTickets);
	}
}
