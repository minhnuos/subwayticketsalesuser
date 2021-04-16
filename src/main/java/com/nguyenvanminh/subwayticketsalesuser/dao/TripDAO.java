package com.nguyenvanminh.subwayticketsalesuser.dao;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.entity.Trip;

public interface TripDAO {

	public List<Trip> trips();
	public Trip findTripById(int id);
	
	public void updateTrip(Trip trip);
}
