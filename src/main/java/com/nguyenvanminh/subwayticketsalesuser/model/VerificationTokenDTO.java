package com.nguyenvanminh.subwayticketsalesuser.model;

public class VerificationTokenDTO {

	private int id;
	private String token;
	private UsersDTO usersDTO;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UsersDTO getUsersDTO() {
		return usersDTO;
	}
	public void setUsersDTO(UsersDTO usersDTO) {
		this.usersDTO = usersDTO;
	}
	
	
}
