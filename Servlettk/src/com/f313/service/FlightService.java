package com.f313.service;

import java.sql.SQLException;
import java.util.List;

import com.f313.entity.Flight;

public interface FlightService {
	public Flight findFlightById(String flightId) throws Exception;
	public List<Flight> findAllFlight() throws Exception;
	public boolean addFlight(String id, String time, String start,
			String destination, int remainSeat, float price) throws Exception;
	public void delFlight(String flightId) throws SQLException;
}
