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

public class CheckFlightAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	private static final long serialVersionUID = 1L;
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("CheckFlightServlet");
	
	private String res;
	
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}
	
	public String checkFlight(){
		

		String flightId = request.getParameter("fid");
		
		if( flightId == null || flightId.equals("")){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
		else{
			try {
				Flight f = flightService.findFlightById(flightId);
				if( f == null ){
					Flag flag = new Flag();
					flag.setSign("two");
					flag.setUrl("/tk/manager.html");
					res = JSONObject.fromObject(flag).toString();
					return "suc";
				}
				else{
				    res = JSONObject.fromObject(f).toString();
					return "suc";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "suc";
		
	}
	
}
