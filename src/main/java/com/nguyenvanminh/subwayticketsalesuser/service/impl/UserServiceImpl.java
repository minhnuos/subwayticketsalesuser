package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.UserDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.model.UsersDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.UserService;

@Service 
@Transactional 
public class UserServiceImpl implements UserService{

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	UserDAO userDAO;

	@Override
	public void addUserDTO(UsersDTO usersDTO) {
		Users users = new Users();
		users.setEnabled(false);
		users.setUsername(usersDTO.getUsername());
		users.setRole(usersDTO.getRole());
		users.setPassword(this.passwordEncoder.encode(usersDTO.getPassword()));
		this.userDAO.addUsers(users);

	}

	@Override
	public void updateUser(UsersDTO usersDTO) {
		Users users = this.userDAO.findUserById(usersDTO.getId());
		if(users != null) {
			users.setEnabled(true);
			this.userDAO.updateUser(users);
		}
		
	}
	
	
}
