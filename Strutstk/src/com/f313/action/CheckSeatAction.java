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

public class CheckSeatAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("CheckSeatServlet");
	
	private static final SeatService seatService;
	static{
		seatService = new SeatServiceImpl();
	}
	
	private String res;
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}
	
	public String checkSeat(){
		String uid = request.getParameter("uid");
		String fid = request.getParameter("fid");
		
		if( uid == null || uid.equals("") || fid.equals("") || fid == null){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
		else{
			try {
				Seat s = seatService.findById(uid, fid);
				if( s == null ){
					Flag flag = new Flag();
					flag.setSign("two");
					flag.setUrl("/tk/manager.html");
					res = JSONObject.fromObject(flag).toString();
					return "suc";
				}
				else{
					res = JSONObject.fromObject(s).toString();
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
