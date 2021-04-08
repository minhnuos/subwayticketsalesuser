package com.nguyenvanminh.subwayticketsalesuser.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.nguyenvanminh.subwayticketsalesuser.dao.VerificationTokenDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.service.UserService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

	@Autowired
    private UserService service;
 
	@Autowired
	VerificationTokenDAO verificationTokenDAO;
	
   
 
    @Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
        this.confirmRegistration(event);
		
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		Users user = event.getUser();
        String token = UUID.randomUUID().toString();
       
        	verificationTokenDAO.createVerificationToken(token, user);
        	 String recipientAddress = user.getUsername();
             String subject = "Registration Confirmation";
             String confirmationUrl = event.getAppUrl() + "/regitrationConfirm?token=" + token;
             String message = "vui long xac nhan email de kich hoat tai khoan " +  event.getLocale();
             SimpleMailMessage email = new SimpleMailMessage();
             email.setTo(recipientAddress);
             email.setSubject(subject);
             email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
             mailSender.send(email);

        
       
	}

}
