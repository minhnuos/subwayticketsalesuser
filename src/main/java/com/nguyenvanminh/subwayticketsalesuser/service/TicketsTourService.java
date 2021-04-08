package com.nguyenvanminh.subwayticketsalesuser.service;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.model.TicketsTourDTO;

public interface TicketsTourService {

	public List<TicketsTourDTO> listTicketsTourDTOs();
	
	public List<TicketsTourDTO> ticketsTourDTOsByStopPointStartAndStopintEnd(int stopPointStart_id,int stopPointEnd_id);
}
