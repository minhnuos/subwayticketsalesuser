package com.nguyenvanminh.subwayticketsalesuser.dao;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.entity.DepartureTime;


public interface DepartureTimeDAO {
	
	public List<DepartureTime> listDepartureTimes();
	
	public void addDepartureTime(DepartureTime departureTime);
	
	
}
