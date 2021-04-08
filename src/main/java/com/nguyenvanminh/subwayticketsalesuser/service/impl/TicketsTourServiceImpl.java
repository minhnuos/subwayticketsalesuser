package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;
import com.nguyenvanminh.subwayticketsalesuser.model.DayDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.RouteDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.StopPointDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.TicketsTourDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.TicketsTourService;

@Service
@Transactional
public class TicketsTourServiceImpl implements TicketsTourService{

	@Autowired
	TicketsTourDAO ticketsTourDAO;

	@Override
	public List<TicketsTourDTO> listTicketsTourDTOs() {
		// TODO Auto-generated method stub
		List<TicketsTour> ticketsTours = this.ticketsTourDAO.listTicketsTours();
		List<TicketsTourDTO> ticketsTourDTOs = new ArrayList<TicketsTourDTO>();
		for (TicketsTour ticketsTour : ticketsTours) {
			TicketsTourDTO ticketsTourDTO  = new TicketsTourDTO();
			ticketsTourDTO.setId(ticketsTour.getId());
			ticketsTourDTO.setPrice(ticketsTour.getPrice());
			ticketsTourDTO.setQuantity(ticketsTour.getQuantity());
			ticketsTourDTO.setRemain(ticketsTour.getRemain());
			DayDTO dayDTO = new DayDTO();
			dayDTO.setId(ticketsTour.getDay().getId());
			dayDTO.setDay(ticketsTour.getDay().getDay());
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
		return ticketsTourDTOs;
	}

	@Override
	public List<TicketsTourDTO> ticketsTourDTOsByStopPointStartAndStopintEnd(int stopPointStart_id,
			int stopPointEnd_id) {
		List<TicketsTourDTO> ticketsTourDTOs = this.listTicketsTourDTOs();
		List<TicketsTourDTO> ticketsTourDTOsFormat = new ArrayList<TicketsTourDTO>();
		String firstDay =  LocalDate.now().plusDays(1).toString();
		int index = this.findIndexTicksTourDTOByDay(firstDay, ticketsTourDTOs);
		for (int i = 0; i < ticketsTourDTOs.size() ; i++) {
			if(i >= index &&
				ticketsTourDTOs.get(i).getRouteDTO().getStopPointDTOStart().getId() == stopPointStart_id &&
				ticketsTourDTOs.get(i).getRouteDTO().getStopPointDTOEnd().getId() == stopPointEnd_id
			) {
				ticketsTourDTOsFormat.add(ticketsTourDTOs.get(i));
			}
		}
		return ticketsTourDTOsFormat;
	}
	
	public int findIndexTicksTourDTOByDay(String day, List<TicketsTourDTO> ticketsTourDTOs) {
		
		int index = -1;
		for (int i = 0; i < ticketsTourDTOs.size(); i++) {
			if(ticketsTourDTOs.get(i).getDayDTO().getDay().equals(day)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
