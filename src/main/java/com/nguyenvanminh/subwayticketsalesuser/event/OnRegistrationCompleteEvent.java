package com.nguyenvanminh.subwayticketsalesuser.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.nguyenvanminh.subwayticketsalesuser.entity.Users;


public class OnRegistrationCompleteEvent extends ApplicationEvent   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String appUrl;
    private Locale locale;
    private Users user;

    public OnRegistrationCompleteEvent( Users user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
    
    
    
}
