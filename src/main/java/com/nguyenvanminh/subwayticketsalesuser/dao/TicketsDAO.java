package com.nguyenvanminh.subwayticketsalesuser.dao;

import com.nguyenvanminh.subwayticketsalesuser.entity.Tickets;

public interface TicketsDAO {

	public void updateTickets(Tickets tickets);
	
	public Tickets findTicketsById(int id);
}
