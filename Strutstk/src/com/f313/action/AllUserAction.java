package com.f313.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.User;
import com.f313.service.UserService;
import com.f313.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class AllUserAction  extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllUserServlet");
	
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
	
	public String allUser(){
		List<User> list = new ArrayList<User>();
		try {
			list = userService.findAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res=JSONArray.fromObject(list).toString();
		return "suc";
	}
	
}
