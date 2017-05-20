package com.f313.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;
import com.f313.util.JdbcUtils;

import net.sf.json.JSONObject;

public class CheckFlightServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("CheckFlightServlet");
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String flightId = request.getParameter("fid");
		
		if( flightId == null || flightId.equals("")){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			String reg = JSONObject.fromObject(flag).toString();
			response.getOutputStream().write(reg.getBytes());
		}
		else{
			try {
				Flight f = flightService.findFlightById(flightId);
				if( f == null ){
					Flag flag = new Flag();
					flag.setSign("two");
					flag.setUrl("/tk/manager.html");
					String reg = JSONObject.fromObject(flag).toString();
					response.getOutputStream().write(reg.getBytes());
				}
				else{
					String reg = JSONObject.fromObject(f).toString();
					response.getOutputStream().write(reg.getBytes());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
