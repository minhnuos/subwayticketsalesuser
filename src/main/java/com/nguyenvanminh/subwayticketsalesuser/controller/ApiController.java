package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyenvanminh.subwayticketsalesuser.dao.BookingDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TicketsTourDAO;
import com.nguyenvanminh.subwayticketsalesuser.dao.TripDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.TicketsTour;
import com.nguyenvanminh.subwayticketsalesuser.entity.Trip;
import com.nguyenvanminh.subwayticketsalesuser.model.Cart;
import com.nguyenvanminh.subwayticketsalesuser.model.CustomerDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.MessageResponse;
import com.nguyenvanminh.subwayticketsalesuser.model.ResponseDetailTickets;
import com.nguyenvanminh.subwayticketsalesuser.service.BookingService;

@RestController
@SessionAttributes("cart")
public class ApiController {
	
	@Autowired
	TripDAO tripDAO;
	
	@Autowired 
	TicketsTourDAO ticketsTourDAO;
	
	@Autowired
	BookingService bookingService;
	
	
	@GetMapping("/api/detail-booking")
	public ResponseEntity<List<ResponseDetailTickets>> detailBooking(@RequestParam int id) {
		List<ResponseDetailTickets> detailTickets = this.bookingService.listDetailTickets(id);
		return new ResponseEntity<List<ResponseDetailTickets>>(detailTickets, HttpStatus.OK);
		
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/api/add-cart")
	public ResponseEntity<List<Cart>> addCart(HttpSession session,
			@RequestParam int id, @RequestParam int price, 
			@RequestParam String stopPointStart, 
			@RequestParam String stopPointEnd, 
			@RequestParam String day,
			@RequestParam int tour,
			@RequestParam int time
		) {
		if(session.getAttribute("cart") == null) {
			List<Cart> carts = new ArrayList<Cart>();
			Cart cart = new Cart();
			cart.setId(id);
			cart.setPrice(price);
			cart.setDay(day);
			cart.setQuantity(1);
			cart.setStopPointStart(stopPointStart);
			cart.setStopPointEnd(stopPointEnd);
			cart.setTour(tour);
			cart.setTime(time);
			carts.add(cart);
			session.setAttribute("cart", carts);
		} else {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			int check = this.checkTicketsCart(carts, tour, id);
			if(check >= 0) {
				carts.get(check).setQuantity(carts.get(check).getQuantity() + 1);
			} else {
				Cart cart = new Cart();
				cart.setId(id);
				cart.setPrice(price);
				cart.setDay(day);
				cart.setQuantity(1);
				cart.setTime(time);
				cart.setStopPointStart(stopPointStart);
				cart.setStopPointEnd(stopPointEnd);
				cart.setTour(tour);
				session.setAttribute("cart", carts);
				carts.add(cart);
			}
		}
		
		return new ResponseEntity<List<Cart>> ( (List<Cart>) session.getAttribute("cart"),HttpStatus.OK);
	}
	
	public int checkTicketsCart(List<Cart> carts, int tour, int id) {
		int check = -1;
		for (int i = 0; i < carts.size(); i++) {
			if(carts.get(i).getId() == id && carts.get(i).getTour() == tour) {
				check = i;
				break;
			}
		}
		return check;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/api/delete-cart") 
	public ResponseEntity<List<Cart>> deleteCart(HttpSession session, @RequestParam int id, @RequestParam int tour) {
		
		if(session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			int check = this.checkTicketsCart(carts, tour, id);
			if(check >= 0) {
				carts.remove(check);
			}
		}
		
		return new ResponseEntity<List<Cart>> ( (List<Cart>) session.getAttribute("cart"), HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/api/quantity-cart")
	public int getQuantityCart(HttpSession session) {
		int quantity = 0;
		if(session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			quantity = carts.size();
		}
		return quantity;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/api/change-quantity-cart")
	public ResponseEntity<Cart> changeQuantityCart(HttpSession session,
			@RequestParam int quantity, @RequestParam int id,
			@RequestParam int tour
		) {
		if(session.getAttribute("cart") != null && quantity > 0) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			int check = this.checkTicketsCart(carts, tour, id);
			if(check >= 0) {
				carts.get(check).setQuantity(quantity);
				return new ResponseEntity<Cart> (carts.get(check), HttpStatus.OK);
			}
		}
		return null;
	}
	@PostMapping("/api/booking")
	public ResponseEntity<MessageResponse> booking(HttpSession session,
			@ModelAttribute CustomerDTO customerDTO, HttpServletRequest request
		) {
		if(session.getAttribute("cart") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			if(carts.size() == 0) {
				return new ResponseEntity<MessageResponse> (new MessageResponse(false, "giỏ hàng rỗng"), HttpStatus.OK);

			} else {
				for (Cart cart : carts) {
					if(cart.getTime() == -1) {
						TicketsTour ticketsTour = this.ticketsTourDAO.findTicketsTourById(cart.getId());
						if(cart.getQuantity() <= ticketsTour.getRemain()) {
							
						} else {
							
							return new ResponseEntity<MessageResponse> (new MessageResponse(false, "vượt quá số lượng"), HttpStatus.OK);
						}
					} else {
						Trip trip = this.tripDAO.findTripById(cart.getId());
						if(cart.getQuantity() <= trip.getRemain()) {
							
						} else {
							return new ResponseEntity<MessageResponse> (new MessageResponse(false, "vượt quá số lượng"), HttpStatus.OK);
						}
					}
				}
				
				this.bookingService.addBookingDTO(customerDTO, carts);
				
				carts.clear();
				return new ResponseEntity<MessageResponse> (new MessageResponse(true, "Đặt vé thành công"), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<MessageResponse> (new MessageResponse(false, "giỏ hàng rỗng"), HttpStatus.OK);
		}
	}
}
