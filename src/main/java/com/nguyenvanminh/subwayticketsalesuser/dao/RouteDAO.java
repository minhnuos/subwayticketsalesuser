package com.nguyenvanminh.subwayticketsalesuser.dao;

import java.util.List;

import com.nguyenvanminh.subwayticketsalesuser.entity.Route;


public interface RouteDAO {

	public List<Route> listRoute();
	
	public void addRoute(Route route);
	
	public Route findRouteByStart_idAndEnd_id(int Start_id, int End_id);
}
