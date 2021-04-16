package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenvanminh.subwayticketsalesuser.dao.UserDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.VerificationTokenDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.event.OnRegistrationCompleteEvent;
import com.nguyenvanminh.subwayticketsalesuser.model.DayDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.StopPointDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.TicketsTourDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.UsersDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.VerificationTokenDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.DayService;
import com.nguyenvanminh.subwayticketsalesuser.service.StopPointService;
import com.nguyenvanminh.subwayticketsalesuser.service.TicketsTourService;
import com.nguyenvanminh.subwayticketsalesuser.service.UserService;
import com.nguyenvanminh.subwayticketsalesuser.service.VerificationTokenService;

@Controller
public class MainController {

	
	@Autowired
	StopPointService stopPointService;
	
	@Autowired
	DayService dayService;
	
	@Autowired
	TicketsTourService ticketsTourService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	VerificationTokenService verificationTokenService;
	
	@GetMapping("/")
	public String home(ModelMap map) {
		List<StopPointDTO> listStopPointDTOs = this.stopPointService.listStopPointDTOs();
		map.addAttribute("listStopPointDTOs", listStopPointDTOs);
		
		String firstDay =  LocalDate.now().plusDays(1).toString();
		List<DayDTO> listDayDTOs = this.dayService.listDayDTOFromFirstDay(firstDay);
		map.addAttribute("firstDay", firstDay);
		DayDTO lastDay = listDayDTOs.get(listDayDTOs.size() - 1);
		map.addAttribute("lastDay", lastDay);
		
		List<TicketsTourDTO> ticketsTourDTOs = new ArrayList<TicketsTourDTO>();
		for (DayDTO dayDTO : listDayDTOs) {
			for (TicketsTourDTO ticketsTourDTO : dayDTO.getTicketsTourDTOs()) {
				ticketsTourDTOs.add(ticketsTourDTO);
			}
		}
		
		map.addAttribute("ticketsTourDTOs", ticketsTourDTOs);
		
		return "pages/home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "pages/registration";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(ModelMap map,
			HttpServletRequest request,
			@RequestParam String username, @RequestParam String password
		) {
		
		Users users = this.userDAO.findUserByEmail(username);
		if(users == null) {
			UsersDTO usersDTO = new UsersDTO();
			usersDTO.setUsername(username);
			usersDTO.setPassword(password);
			usersDTO.setRole("ROLE_USER");
			
			userService.addUserDTO(usersDTO);
			String appUrl = request.getContextPath();
			Users user = this.userDAO.findUserByEmail(username);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
			map.addAttribute("message", "Vui lòng xác nhận email!");
			return "pages/registration";
		}else {
			map.addAttribute("message", "email này đã tồn tại!");
			return "pages/registration";
		}
		
		
	}
	
	@GetMapping("/regitrationConfirm")
	public String regitrationConfirm(@RequestParam String token, ModelMap map) {
		VerificationTokenDTO verificationTokenDTO = this.verificationTokenService.findVerificationTokenDTOByToken(token);
		if (verificationTokenDTO != null) {
			this.userService.updateUser(verificationTokenDTO.getUsersDTO());
			map.addAttribute("message", "Kích hoạt tài khoản thành công!");
		} else {
			map.addAttribute("message", "Kích hoạt tài khoản thất bại!");
		}
		return "pages/confirm";
	}
}
