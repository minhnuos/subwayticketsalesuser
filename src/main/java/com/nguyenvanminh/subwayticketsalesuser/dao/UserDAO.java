package com.nguyenvanminh.subwayticketsalesuser.dao;

import com.nguyenvanminh.subwayticketsalesuser.entity.Users;

public interface UserDAO {

	public Users findUserByEmail(String email);
	
	public void addUsers(Users users);
	
	public void updateUser(Users users);
	
	public Users findUserById(int id);
}
