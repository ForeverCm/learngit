package com.f313.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.dao.UserDAO;
import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;

import net.sf.json.JSONObject;

public class DeleteFlightServlet extends HttpServlet{

	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("DeleteFlightServlet");
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
         String fid = request.getParameter("fid");
         Flight flight = new Flight();
		try {
			flight = flightService.findFlightById(fid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( fid == null || fid.equals("") || flight == null ){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			String reg = JSONObject.fromObject(flag).toString();
			response.getOutputStream().write(reg.getBytes());
		}
		else{
			try {
				flightService.delFlight(fid);;
				Flag flag = new Flag();
				flag.setSign("one");
				flag.setUrl("/tk/manager.html");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
