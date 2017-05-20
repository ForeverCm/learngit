package com.f313.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.entity.Flight;
import com.f313.entity.Seat;
import com.f313.mark.Flag;
import com.f313.service.FlightService;
import com.f313.service.SeatService;
import com.f313.service.impl.FlightServiceImpl;
import com.f313.service.impl.SeatServiceImpl;

import net.sf.json.JSONObject;

public class DeleteSeatServlet extends HttpServlet{
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("DeleteSeatServlet");
	
	private static final SeatService seatService;
	static{
		seatService = new SeatServiceImpl();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		 String uid = request.getParameter("uid");
         String fid = request.getParameter("fid");
         Seat seat = new Seat();
		try {
			seat = seatService.findById(uid, fid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( fid == null || fid.equals("") || uid == null || uid.equals("")|| seat == null ){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			String reg = JSONObject.fromObject(flag).toString();
			response.getOutputStream().write(reg.getBytes());
		}
		else{
			try {
			    seatService.deleteSeat(uid, fid);
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
