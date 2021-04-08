package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.StopPointDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.StopPoint;

@Repository
@Transactional
public class StopPointDAOImpl implements StopPointDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<StopPoint> listStopPoints() {
		// TODO Auto-generated method stub
		String jql = "SELECT s FROM StopPoint s";
		List<StopPoint> listStopPoints = this.entityManager.createQuery(jql, StopPoint.class ).getResultList();
		return listStopPoints;
	}

}
