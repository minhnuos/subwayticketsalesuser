package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.BookingDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Booking;

@Transactional
@Repository
public class BookingDAOImpl implements BookingDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void addBooking(Booking booking) {
		// TODO Auto-generated method stub
		this.entityManager.persist(booking);
	}
}
