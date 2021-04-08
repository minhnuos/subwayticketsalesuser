package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.DayDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Day;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;
import com.nguyenvanminh.subwayticketsalesuser.model.DayDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.RouteDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.StopPointDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.TicketsTourDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.DayService;

@Service
@Transactional
public class DayServiceImpl implements DayService{

	@Autowired
	DayDAO dayDAO;

	@Override
	public List<DayDTO> listDayDTOs() {
		List<Day> listDays = this.dayDAO.listDays();
		List<DayDTO> listDayDTOs = new ArrayList<DayDTO>();
		for (Day day : listDays) {
			DayDTO dayDTO = new DayDTO();
			dayDTO.setId(day.getId());
			dayDTO.setDay(day.getDay());
			
			List<TicketsTour> ticketsTours = day.getListTicketsTours();
			List<TicketsTourDTO> ticketsTourDTOs = new ArrayList<TicketsTourDTO>();
			for (TicketsTour ticketsTour : ticketsTours) {
				TicketsTourDTO ticketsTourDTO  = new TicketsTourDTO();
				ticketsTourDTO.setId(ticketsTour.getId());
				ticketsTourDTO.setPrice(ticketsTour.getPrice());
				ticketsTourDTO.setQuantity(ticketsTour.getQuantity());
				ticketsTourDTO.setDayDTO(dayDTO);
				
				RouteDTO routeDTO = new RouteDTO();
				StopPointDTO stopPointDTOStart = new StopPointDTO();
				stopPointDTOStart.setId(ticketsTour.getRoute().getStopPointStart().getId());
				stopPointDTOStart.setAddress(ticketsTour.getRoute().getStopPointStart().getAddress());
				stopPointDTOStart.setName(ticketsTour.getRoute().getStopPointStart().getName());
				routeDTO.setStopPointDTOStart(stopPointDTOStart);
				StopPointDTO stopPointDTOEnd = new StopPointDTO();
				stopPointDTOEnd.setId(ticketsTour.getRoute().getStopPointEnd().getId());
				stopPointDTOEnd.setAddress(ticketsTour.getRoute().getStopPointEnd().getAddress());
				stopPointDTOEnd.setName(ticketsTour.getRoute().getStopPointEnd().getName());
				routeDTO.setStopPointDTOEnd(stopPointDTOEnd);
				ticketsTourDTO.setRouteDTO(routeDTO);
				
				ticketsTourDTOs.add(ticketsTourDTO);
			}
			dayDTO.setTicketsTourDTOs(ticketsTourDTOs);
			listDayDTOs.add(dayDTO);
		}
		return listDayDTOs;
	}

	@Override
	public List<DayDTO> listDayDTOFromFirstDay(String day) {
		List<DayDTO> dayDTOs = this.listDayDTOs();
		int index = this.FindIndex(dayDTOs, day);
		List<DayDTO> dayDTOFormats = new ArrayList<DayDTO>();
		for (int i = 0; i < dayDTOs.size(); i++) {
			if(i >= index) {
				dayDTOFormats.add(dayDTOs.get(i));
			}
		}
		return dayDTOFormats;
	}
	
	public int FindIndex(List<DayDTO> dayDTOs, String day) {
		int index = 1;
		for (int i = 0 ; i < dayDTOs.size();i++) {
			if(dayDTOs.get(i).getDay().equals(day)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
}
