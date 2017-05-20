package com.f313.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.f313.dao.FlightDAO;
import com.f313.entity.Flight;
import com.f313.service.FlightService;

public class FlightServiceImpl implements FlightService{
	
	private static final FlightDAO flightDao;
	static{
		flightDao = new FlightDAO();
	}
	
	public List<Flight> findAllFlight() throws Exception{
		List<Flight> list = null;
		list = flightDao.findAllFlight();
		return list;
	}
	public Flight findFlightById(String flightId) throws Exception{
		Flight flight = null;
		flight = flightDao.findById(flightId);
		return flight;
	}
	public boolean addFlight(String id, String time, String start,
			String destination, int remainSeat, float price) throws Exception{
		return flightDao.addFlight(id, time, start, destination, remainSeat, price);
	}
	public void delFlight(String flightId) throws SQLException{
		flightDao.deleteFlight(flightId);
	}
}
