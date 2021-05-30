package com.nguyenvanminh.subwayticketsalesuser.service;

import java.util.ArrayList;
import java.util.List;


import com.nguyenvanminh.subwayticketsalesuser.model.BookingDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.Cart;
import com.nguyenvanminh.subwayticketsalesuser.model.CustomerDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.ResponseBooking;
import com.nguyenvanminh.subwayticketsalesuser.model.ResponseDetailTickets;

public interface BookingService {

	public void addBookingDTO(CustomerDTO customerDTO, List<Cart> carts);
	
	public List<ResponseBooking> listResponseBookings();
	
	
	public List<ResponseDetailTickets> listDetailTickets(int id) ;
}
