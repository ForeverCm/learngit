package com.f313.action;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.SeatService;
import com.f313.service.UserService;
import com.f313.service.impl.FlightServiceImpl;
import com.f313.service.impl.SeatServiceImpl;
import com.f313.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class BookSeatAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("BookSeatServlet");
	
	private static final SeatService seatService;
	static{
		seatService = new SeatServiceImpl();
	}
	private static final FlightService flightService;
	static{
		flightService = new FlightServiceImpl();
	}
	private static final UserService userService;
	static{
		userService = new UserServiceImpl();
	}
	private String res;
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String bookSeat(){
		String uid = request.getParameter("uid");
		String fid = request.getParameter("fid");
		Flag flag = new Flag();
		flag.setSign("");
		flag.setUrl("/tk/login.jsp");
		
		try {
			
			 if(uid == null || uid.equals("") || userService.findUserById(uid) == null){
				flag.setSign("four");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
				
			}
			else if(fid == null || fid.equals("") || flightService.findFlightById(fid) == null){
				flag.setSign("three");
				res= JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else if(seatService.addSeat(uid, fid)){
				flag.setSign("one");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else{
				flag.setSign("two");
				res = JSONObject.fromObject(flag).toString();
			    return "suc";
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "suc";
		
	}
	}
	

