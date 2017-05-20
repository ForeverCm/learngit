package com.f313.action;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class AddFlightAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllFlightServlet");
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	private String res;
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}
	public String addFlight(){
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
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else{
				flag.setSign("two");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
		} catch (Exception e) {
			return "suc";
		}
	}
	}

