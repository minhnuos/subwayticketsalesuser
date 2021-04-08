package com.nguyenvanminh.subwayticketsalesuser.service;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.model.DayDTO;

public interface DayService {
	
	public List<DayDTO> listDayDTOs();
	
	public List<DayDTO> listDayDTOFromFirstDay(String day);
}
