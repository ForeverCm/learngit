package com.f313.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;

import net.sf.json.JSONObject;

public class AddFlightServlet extends HttpServlet {
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllFlightServlet");
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String time = request.getParameter("time");
		String start = request.getParameter("start");
		String destination = request.getParameter("destination");
		int seat = Integer.parseInt(request.getParameter("seat"));
		float price = Float.parseFloat(request.getParameter("price"));
		
		Flag flag = new Flag();
		flag.setSign("one");
		flag.setUrl("/tk/login.jsp");
		
		try {
			if(flightService.addFlight(id, time, start, destination, seat, price)){
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
			else{
				flag.setSign("two");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
		} catch (Exception e) {
			response.sendRedirect("mistake.jsp");
		}
	}
}
