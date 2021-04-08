package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.VerificationTokenDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.VerificationToken;
import com.nguyenvanminh.subwayticketsalesuser.model.UsersDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.VerificationTokenDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.VerificationTokenService;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService{

	@Autowired
	VerificationTokenDAO verificationTokenDAO;

	@Override
	public VerificationTokenDTO findVerificationTokenDTOByToken(String token) {
		VerificationToken verificationToken = this.verificationTokenDAO.findVerificationTokenByToken(token);
		if(verificationToken != null) {
			VerificationTokenDTO verificationTokenDTO = new VerificationTokenDTO();
			verificationTokenDTO.setId(verificationToken.getId());
			verificationTokenDTO.setToken(verificationToken.getToken());
			
			UsersDTO usersDTO = new UsersDTO();
			usersDTO.setId(verificationToken.getUser().getId());
			verificationTokenDTO.setUsersDTO(usersDTO);
			return verificationTokenDTO;
		}
		return null;
	}
}
