package com.f313.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.mark.Flag;
import com.f313.service.ManagerService;
import com.f313.service.UserService;
import com.f313.service.impl.ManagerServiceImpl;
import com.f313.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserService userService;
	private static final ManagerService managerService;
	
	
	
	private HttpServletRequest request;
	
	static{
		userService = new UserServiceImpl();
	}
	static{
		managerService = new ManagerServiceImpl();
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	Flag flag = new Flag();
	private String res;
	

	public String excuteAjax(){
	String pid = request.getParameter("pid");
	String psw = request.getParameter("psw");
	String iden = request.getParameter("iden");
	
	
	
	//System.out.println("aaaaaaaaaaaaaaaaaaa");
	
	if( iden.equals("user")){
		try {
			if(userService.login(pid, psw)){
				flag.setSign("one");
				flag.setUrl("/tk/user.html");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else{
				flag.setSign("two");
				flag.setUrl("/tk/user.html");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
		} catch (Exception e) {
			flag.setSign("three");
			flag.setUrl("/tk/user.html");
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
		
	}
	else{
		try {
			if(managerService.login(pid, psw)){
				flag.setSign("four");
				flag.setUrl("/tk/manager.html");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
			else{
				flag.setSign("two");
				flag.setUrl("/tk/user.html");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
		} catch (Exception e) {
			flag.setSign("six");
			flag.setUrl("/tk/user.html");
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
	}
	}


	public String getRes() {
		return res;
	}


	public void setRes(String res) {
		this.res = res;
	}
	
}
	

