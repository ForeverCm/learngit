package com.f313.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.Flight;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class AllFlightAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllFlightServlet");
	
	private static final FlightService flightService;
	
	private String res;

	static{
		flightService = new FlightServiceImpl();
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	public String allFlight(){
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
		 res=JSONArray.fromObject(list).toString();
		return "suc";
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}
	
}
