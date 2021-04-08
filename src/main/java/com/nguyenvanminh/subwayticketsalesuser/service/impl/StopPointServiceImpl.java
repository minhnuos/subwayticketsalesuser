package com.nguyenvanminh.subwayticketsalesuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvanminh.subwayticketsalesuser.dao.StopPointDAO;
import com.nguyenvanminh.subwayticketsalesuser.entity.StopPoint;
import com.nguyenvanminh.subwayticketsalesuser.model.StopPointDTO;
import com.nguyenvanminh.subwayticketsalesuser.service.StopPointService;

@Service
@Transactional
public class StopPointServiceImpl implements StopPointService{

	@Autowired
	StopPointDAO stopPointDAO;

	@Override
	public List<StopPointDTO> listStopPointDTOs() {
		// TODO Auto-generated method stub
		List<StopPoint> listStopPoints = this.stopPointDAO.listStopPoints();
		List<StopPointDTO> listStopPointDTOs = new ArrayList<StopPointDTO>();
		for (StopPoint stopPoint : listStopPoints) {
			StopPointDTO stopPointDTO = new StopPointDTO();
			stopPointDTO.setId(stopPoint.getId());
			stopPointDTO.setAddress(stopPoint.getAddress());
			stopPointDTO.setName(stopPoint.getName());
			listStopPointDTOs.add(stopPointDTO);
		}
		return listStopPointDTOs;
	}
}
