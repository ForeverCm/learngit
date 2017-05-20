package com.f313.action;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.Seat;
import com.f313.mark.Flag;
import com.f313.service.SeatService;
import com.f313.service.impl.SeatServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class DeleteSeatAction  extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("DeleteSeatServlet");
	
	private static final SeatService seatService;
	static{
		seatService = new SeatServiceImpl();
	}
	private String res;
	
	public String deleteSeat(){
		
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
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
		else{
			try {
			    seatService.deleteSeat(uid, fid);
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
		
	

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}


}
