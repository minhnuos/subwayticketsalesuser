package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.BookingTicketsTourDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.BookingTicketsTour;

@Repository
@Transactional
public class BookingTicketsTourDAOImpl implements BookingTicketsTourDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addBookingTicketsTour(BookingTicketsTour bookingTicketsTour) {
		// TODO Auto-generated method stub
		this.entityManager.persist(bookingTicketsTour);
	}
}
