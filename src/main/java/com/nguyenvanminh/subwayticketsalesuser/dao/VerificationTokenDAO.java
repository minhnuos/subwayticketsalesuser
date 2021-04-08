package com.nguyenvanminh.subwayticketsalesuser.dao;

import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.entity.VerificationToken;

public interface VerificationTokenDAO {

	public void createVerificationToken(String token, Users user);
	
	public VerificationToken findVerificationTokenByToken(String token);
}
