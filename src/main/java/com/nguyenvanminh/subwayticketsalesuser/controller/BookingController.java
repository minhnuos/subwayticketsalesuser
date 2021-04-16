package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyenvanminh.subwayticketsalesuser.model.ResponseBooking;
import com.nguyenvanminh.subwayticketsalesuser.service.BookingService;

@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/history")
	public String historyBooking(ModelMap map) {
		List<ResponseBooking> listResponseBookings = this.bookingService.listResponseBookings();
		map.addAttribute("listResponseBookings", listResponseBookings);
		return "pages/booking";
		
	}
}
