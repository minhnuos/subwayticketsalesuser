package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.DayDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Day;

@Repository
@Transactional
public class DayDAOImpl implements DayDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Day> listDays() {
		// TODO Auto-generated method stub
		String jql = "SELECT d FROM Day d";
		List<Day> listDays = this.entityManager.createQuery(jql, Day.class).getResultList();
		return listDays;
	}
}
