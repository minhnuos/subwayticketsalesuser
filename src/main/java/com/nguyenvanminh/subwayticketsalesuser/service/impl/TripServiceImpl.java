package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.TripDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.Trip;
import com.nguyenvanminh.subwayticketsalesuser.model.DayDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.DepartureTimeDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.RouteDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.StopPointDTO;
import com.nguyenvanminh.subwayticketsalesuser.model.TripDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.TripService;

@Transactional
@Service
public class TripServiceImpl implements TripService{

	@Autowired
	TripDAO tripDAO;

	@Override
	public List<TripDTO> findTripDTOByDayAndRoutes(int stopPointDTOStart_id, int stopPointDTOEnd_id, String day) {
		List<Trip> trips = this.tripDAO.trips();
		List<TripDTO> tripDTOs = new ArrayList<TripDTO>();
		for (Trip trip : trips) {
			if(trip.getDay().getDay().equals(day) && 
					trip.getRoute().getStopPointStart().getId() == stopPointDTOStart_id &&
					trip.getRoute().getStopPointEnd().getId() == stopPointDTOEnd_id
			) 
			{
				TripDTO tripDTO = new TripDTO();
				tripDTO.setId(trip.getId());
				tripDTO.setPrice(trip.getPrice());
				tripDTO.setRemain(trip.getRemain());
				DepartureTimeDTO departureTimeDTO = new DepartureTimeDTO();
				departureTimeDTO.setId(trip.getDepartureTime().getId());
				departureTimeDTO.setTime(trip.getDepartureTime().getTime());
				tripDTO.setDepartureTimeDTO(departureTimeDTO);
				
				RouteDTO routeDTO = new RouteDTO();
				StopPointDTO stopPointDTOStart = new StopPointDTO();
				stopPointDTOStart.setId(trip.getRoute().getStopPointStart().getId());
				stopPointDTOStart.setAddress(trip.getRoute().getStopPointStart().getAddress());
				stopPointDTOStart.setName(trip.getRoute().getStopPointStart().getName());
				routeDTO.setStopPointDTOStart(stopPointDTOStart);
				StopPointDTO stopPointDTOEnd = new StopPointDTO();
				stopPointDTOEnd.setId(trip.getRoute().getStopPointEnd().getId());
				stopPointDTOEnd.setAddress(trip.getRoute().getStopPointEnd().getAddress());
				stopPointDTOEnd.setName(trip.getRoute().getStopPointEnd().getName());
				routeDTO.setStopPointDTOEnd(stopPointDTOEnd);
				tripDTO.setRouteDTO(routeDTO);
				
				DayDTO dayDTO = new DayDTO();
				dayDTO.setId(trip.getDay().getId());
				dayDTO.setDay(trip.getDay().getDay());
				tripDTO.setDayDTO(dayDTO);
				
				tripDTOs.add(tripDTO);
				
				
			}
		}
		return tripDTOs;
	}

	
}
