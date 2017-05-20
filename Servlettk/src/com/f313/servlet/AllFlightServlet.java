package com.f313.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.service.FlightService;
import com.f313.service.UserService;
import com.f313.service.impl.FlightServiceImpl;
import com.f313.service.impl.UserServiceImpl;

import net.sf.json.JSONArray;

public class AllFlightServlet extends HttpServlet{
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllFlightServlet");
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<Flight> list = new ArrayList<Flight>();
		try {
			list = flightService.findAllFlight();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=JSONArray.fromObject(list).toString();
		response.getOutputStream().write(result.getBytes());
	}
}
