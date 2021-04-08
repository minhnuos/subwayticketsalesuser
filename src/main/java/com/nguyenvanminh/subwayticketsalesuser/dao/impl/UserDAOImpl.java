package com.nguyenvanminh.subwayticketsalesuser.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.UserDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Users findUserByEmail(String email) {
		String jql = "SELECT u FROM Users u WHERE username='" + email + "'";
		try {
			Users user = this.entityManager.createQuery(jql, Users.class).getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
		
		
	}

	@Override
	public void addUsers(Users users) {
		this.entityManager.persist(users);
	}

	@Override
	public void updateUser(Users users) {
		// TODO Auto-generated method stub
		this.entityManager.merge(users);
	}

	@Override
	public Users findUserById(int id) {
		Users users = this.entityManager.find(Users.class, id);
		return users;
	}
}
