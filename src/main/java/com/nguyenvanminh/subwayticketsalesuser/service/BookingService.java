package com.nguyenvanminh.subwayticketsalesuser.service;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.model.BookingDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.Cart;
import com.nguyenvanminh.subwayticketsalesuser.model.CustomerDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.ResponseBooking;

public interface BookingService {

	public void addBookingDTO(CustomerDTO customerDTO, List<Cart> carts);
	
	public List<ResponseBooking> listResponseBookings();
}
