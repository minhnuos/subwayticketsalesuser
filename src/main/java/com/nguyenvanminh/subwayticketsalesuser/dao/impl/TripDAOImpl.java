package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.TripDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Trip;

@Repository
@Transactional
public class TripDAOImpl implements TripDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Trip> trips() {
		// TODO Auto-generated method stub
		String jql = "SELECT t FROM Trip t";
		List<Trip> trips = this.entityManager.createQuery(jql, Trip.class).getResultList();
		return trips;
	}
	
	
}
