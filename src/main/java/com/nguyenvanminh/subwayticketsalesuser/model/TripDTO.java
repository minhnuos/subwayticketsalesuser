package com.nguyenvanminh.subwayticketsalesuser.model;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TripDTO {

	private int id;
	
	private DayDTO dayDTO;
	
	private DepartureTimeDTO departureTimeDTO;
	
	private RouteDTO routeDTO;
	
	private int quantity;

	private int price;
	
	private List<TicketsDTO> ticketsDTOs;
	private int remain;
	
	public String priceFormat() {
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String result = currencyVN.format(this.price);
		return result;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}
	public List<TicketsDTO> getTicketsDTOs() {
		return ticketsDTOs;
	}

	public void setTicketsDTOs(List<TicketsDTO> ticketsDTOs) {
		this.ticketsDTOs = ticketsDTOs;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DayDTO getDayDTO() {
		return dayDTO;
	}

	public void setDayDTO(DayDTO dayDTO) {
		this.dayDTO = dayDTO;
	}

	public DepartureTimeDTO getDepartureTimeDTO() {
		return departureTimeDTO;
	}

	public void setDepartureTimeDTO(DepartureTimeDTO departureTimeDTO) {
		this.departureTimeDTO = departureTimeDTO;
	}

	public RouteDTO getRouteDTO() {
		return routeDTO;
	}

	public void setRouteDTO(RouteDTO routeDTO) {
		this.routeDTO = routeDTO;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
