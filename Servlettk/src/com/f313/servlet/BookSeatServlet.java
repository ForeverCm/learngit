package com.f313.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.SeatService;
import com.f313.service.UserService;
import com.f313.service.impl.FlightServiceImpl;
import com.f313.service.impl.SeatServiceImpl;
import com.f313.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;

public class BookSeatServlet extends HttpServlet{
	
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String fid = request.getParameter("fid");
		Flag flag = new Flag();
		flag.setSign("");
		flag.setUrl("/tk/login.jsp");
		
		try {
			
			 if(uid == null || uid.equals("") || userService.findUserById(uid) == null){
				flag.setSign("four");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
			else if(fid == null || fid.equals("") || flightService.findFlightById(fid) == null){
				flag.setSign("three");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
			else if(seatService.addSeat(uid, fid)){
				flag.setSign("one");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
			else{
				flag.setSign("two");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
