package com.f313.action;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.Flight;
import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.impl.FlightServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class DeleteFlightAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("DeleteFlightServlet");
	
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
	
	public String deleteFlight(){
		
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
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else{
				try {
					flightService.delFlight(fid);;
					Flag flag = new Flag();
					flag.setSign("one");
					flag.setUrl("/tk/manager.html");
					res = JSONObject.fromObject(flag).toString();
					return "suc";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "suc";
	}
}
