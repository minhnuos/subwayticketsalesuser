package com.nguyenvanminh.subwayticketsalesuser.service;

import com.nguyenvanminh.subwayticketsalesuser.model.VerificationTokenDTO;

public interface VerificationTokenService {

	public VerificationTokenDTO findVerificationTokenDTOByToken(String token);
}
