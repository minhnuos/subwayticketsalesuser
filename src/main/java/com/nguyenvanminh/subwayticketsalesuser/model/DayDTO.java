package com.nguyenvanminh.subwayticketsalesuser.model;

import java.util.List;

public class DayDTO {

	private int id;
	
	private String day;

	private List<TicketsTourDTO> ticketsTourDTOs;
	
	
	
	public List<TicketsTourDTO> getTicketsTourDTOs() {
		return ticketsTourDTOs;
	}

	public void setTicketsTourDTOs(List<TicketsTourDTO> ticketsTourDTOs) {
		this.ticketsTourDTOs = ticketsTourDTOs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	
	
}
