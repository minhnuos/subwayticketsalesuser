package com.nguyenvanminh.subwayticketsalesuser.service;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.model.TripDTO;

public interface TripService {

	public List<TripDTO> findTripDTOByDayAndRoutes(int stopPointDTOStart_id,int stopPointDTOEnd_id,String day);
}
