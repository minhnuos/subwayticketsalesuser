package com.nguyenvanminh.subwayticketsalesuser.dao;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;

public interface TicketsTourDAO {

	public List<TicketsTour> listTicketsTours();
	
	public TicketsTour findTicketsTourById(int id);
	
	public void updateTicketsTour(TicketsTour ticketsTour);
}
