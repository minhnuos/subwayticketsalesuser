package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenvanminh.subwayticketsalesuser.model.TicketsTourDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.TicketsTourService;

@Controller
public class TicketsTourController {

	@Autowired
	TicketsTourService ticketsTourService;
	
	@GetMapping("/ticketstour")
	public String search(ModelMap map , @RequestParam int stopPointDTOStart_id, @RequestParam int stopPointDTOEnd_id) {
		List<TicketsTourDTO> ticketsTourDTOs = this.ticketsTourService.ticketsTourDTOsByStopPointStartAndStopintEnd(stopPointDTOStart_id, stopPointDTOEnd_id);
		map.addAttribute("ticketsTourDTOs", ticketsTourDTOs);
		return "pages/tour";
	}
}
