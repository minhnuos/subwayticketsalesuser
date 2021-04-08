package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.UserDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;


@Service
@Transactional
public class UserLoginServiceImpl implements UserDetailsService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users = this.userDAO.findUserByEmail(username);
		
		if(users == null) {
			throw new UsernameNotFoundException("no user");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(users.getRole()));
		UserDetails details = new User(users.getUsername(), users.getPassword(), users.isEnabled(), true, true, true, authorities);
		return details;
	}

}
