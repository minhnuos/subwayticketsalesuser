package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nguyenvanminh.subwayticketsalesuser.dao.BookingDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.CustomerDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDetailDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TripDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.UserDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Booking;
import com.nguyenvanminh.subwayticketsalesuser.entity.BookingTickets;
import com.nguyenvanminh.subwayticketsalesuser.entity.BookingTicketsTour;
import com.nguyenvanminh.subwayticketsalesuser.entity.Customer;
import com.nguyenvanminh.subwayticketsalesuser.entity.Tickets;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTourDetail;
import com.nguyenvanminh.subwayticketsalesuser.entity.Trip;
import com.nguyenvanminh.subwayticketsalesuser.entity.Users;
import com.nguyenvanminh.subwayticketsalesuser.model.BookingDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.Cart;
import com.nguyenvanminh.subwayticketsalesuser.model.CustomerDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.ResponseBooking;
import com.nguyenvanminh.subwayticketsalesuser.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{

	@Autowired
	BookingDAO bookingDAO;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	TripDAO tripDAO;
	
	@Autowired
	TicketsDAO ticketsDAO;
	
	@Autowired
	TicketsTourDetailDAO ticketsTourDetailDAO;
	
	@Autowired 
	TicketsTourDAO ticketsTourDAO;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	public void addBookingDTO(CustomerDTO customerDTO, List<Cart> carts) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			Customer customer = new Customer();
			Users users = userDAO.findUserByEmail(authentication.getName()); // lấy user
			List<Booking> bookings = new ArrayList<Booking>(); // khởi tạo list bookings
			Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setStatus(false);
			int total = 0;
			for (Cart cart : carts) {
				total += cart.getPrice()*cart.getQuantity();
			}
			booking.setTotal(total);
			List<BookingTickets> bookingTickets = new ArrayList<BookingTickets>(); // khởi tạo list BookingTickets
			List<BookingTicketsTour> bookingTicketsTours = new ArrayList<BookingTicketsTour>(); // khởi tạo list BookingTicketsTour
			for (Cart cart : carts) {
				if(cart.getTime() != -1) {
					Trip trip = this.tripDAO.findTripById(cart.getId());
					trip.setRemain(trip.getRemain() - cart.getQuantity());
					this.tripDAO.updateTrip(trip);
					int i = 0;
					for (Tickets tickets : trip.getTickets()) {
						if(tickets.isStatus()) {
							BookingTickets bookingTicket = new BookingTickets();
							bookingTicket.setTickets(tickets);
							bookingTicket.setBooking(booking);
							bookingTickets.add(bookingTicket);
		
							Tickets ticketsUpdate = this.ticketsDAO.findTicketsById(tickets.getId()); // thay đổi trạng thái ticket
							ticketsUpdate.setStatus(false);
							this.ticketsDAO.updateTickets(ticketsUpdate);
							i++;
							if(i >= cart.getQuantity()) {
								break;
							}
						}
						
					}
					
				} else {
					TicketsTour ticketsTour = this.ticketsTourDAO.findTicketsTourById(cart.getId());
					ticketsTour.setRemain(ticketsTour.getRemain() - cart.getQuantity());
					this.ticketsTourDAO.updateTicketsTour(ticketsTour);
					int i = 0;
						for (TicketsTourDetail ticketsTourDetail : ticketsTour.getTicketsTourDetails()) {
							if(ticketsTourDetail.isStatus()) {
								BookingTicketsTour bookingTicketsTour = new BookingTicketsTour();
								bookingTicketsTour.setTicketsTourDetail(ticketsTourDetail);
								bookingTicketsTour.setBooking(booking);
								bookingTicketsTours.add(bookingTicketsTour);
								
								TicketsTourDetail ticketsTourDetailUpdate = this.ticketsTourDetailDAO.findTicketsTourDetailById(ticketsTourDetail.getId());  // thay đổi trạng thái ticketsTourDetail
								ticketsTourDetailUpdate.setStatus(false);
								this.ticketsTourDetailDAO.updateTicketsTourDetail(ticketsTourDetailUpdate);
								i++;
								if(i >= cart.getQuantity()) {
									break;
								}
							}
					}
					
				}
			}
			
			booking.setBookingTickets(bookingTickets); 
			booking.setBookingTicketsTours(bookingTicketsTours);
			bookings.add(booking);
			
			
			
			customer.setName(customerDTO.getName());
			customer.setPhone(customerDTO.getPhone());
			customer.setAddress(customerDTO.getAddress());
			customer.setUsers(users);
			customer.setBookings(bookings);
			this.customerDAO.addCustomer(customer);
		}
	}

	@Override
	public List<ResponseBooking> listResponseBookings() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users users = userDAO.findUserByEmail(authentication.getName()); // lấy user
		
		List<ResponseBooking> listResponseBookings = new ArrayList<ResponseBooking>();
		
		for (Customer customer : users.getCustomers()) {
			for (Booking booking : customer.getBookings()) {
				ResponseBooking responseBooking = new ResponseBooking();
				responseBooking.setId(booking.getId());
				responseBooking.setCustomerName(customer.getName());
				responseBooking.setTotal(booking.getTotal());
				responseBooking.setStatus(booking.isStatus());
				responseBooking.setTime(booking.getTime());
				responseBooking.setPhone(customer.getPhone());
				responseBooking.setQuantity(booking.getBookingTickets().size() + booking.getBookingTicketsTours().size());
				listResponseBookings.add(responseBooking);
			}
		}
		
		return listResponseBookings;
	}

	
	
}
