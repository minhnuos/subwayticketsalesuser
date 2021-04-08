package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.VerificationTokenDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.entity.VerificationToken;

@Repository
@Transactional
public class VerificationTokenDAOImpl implements VerificationTokenDAO{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void createVerificationToken(String token, Users user) {
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		this.entityManager.persist(verificationToken);
		
	}
	@Override
	public VerificationToken findVerificationTokenByToken(String token) {
		String jql = "SELECT vt FROM VerificationToken vt WHERE token='" + token + "'";
		try {
			VerificationToken verificationToken = this.entityManager.createQuery(jql, VerificationToken.class).getSingleResult();
			return verificationToken;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
}
