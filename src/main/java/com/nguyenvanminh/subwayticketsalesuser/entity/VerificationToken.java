package com.nguyenvanminh.subwayticketsalesuser.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="verificationtoken")
public class VerificationToken {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String token;
  
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private Users user;
    
   
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

   
    
    

}
