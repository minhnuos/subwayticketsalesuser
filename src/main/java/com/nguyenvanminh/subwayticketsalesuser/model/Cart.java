package com.nguyenvanminh.subwayticketsalesuser.model;

public class Cart {


	private int id;
	
	private int price;
	
	private int quantity;
	
	private String stopPointStart;
	
	private String stopPointEnd;
	
	private String day;

	private int tour;
	
	private int time;
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStopPointStart() {
		return stopPointStart;
	}

	public void setStopPointStart(String stopPointStart) {
		this.stopPointStart = stopPointStart;
	}

	public String getStopPointEnd() {
		return stopPointEnd;
	}

	public void setStopPointEnd(String stopPointEnd) {
		this.stopPointEnd = stopPointEnd;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
}
