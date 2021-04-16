package com.nguyenvanminh.subwayticketsalesuser.dao;

import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTourDetail;

public interface TicketsTourDetailDAO {

	public void updateTicketsTourDetail(TicketsTourDetail ticketsTourDetail);
	
	public TicketsTourDetail findTicketsTourDetailById(int id);
}
