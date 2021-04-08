package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenvanminh.subwayticketsalesuser.model.TripDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.TripService;

@Controller
public class TripController {
	
	@Autowired
	TripService tripService;
	
	@GetMapping("/trip")
	public String searchTickets(ModelMap map , @RequestParam int StopPointDTOStart_id, @RequestParam int StopPointDTOEnd_id, @RequestParam String day) {
		List<TripDTO> tripDTOs = this.tripService.findTripDTOByDayAndRoutes(StopPointDTOStart_id, StopPointDTOEnd_id, day);
		map.addAttribute("tripDTOs", tripDTOs);
		map.addAttribute("day", day);
		return "pages/trip";
	}
}
