package com.f313.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.Seat;
import com.f313.service.SeatService;
import com.f313.service.impl.SeatServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class AllSeatAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllSeatServlet");
	
	private static final SeatService seatService;
	static{
		seatService = new SeatServiceImpl();
	}
	private String res;
	
	public String allSeat(){
		
		List<Seat> list = new ArrayList<Seat>();
		try {
			list = seatService.findAllSeat();
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
