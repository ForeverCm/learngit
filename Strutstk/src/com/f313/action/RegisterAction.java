package com.f313.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.mark.Flag;
import com.f313.service.UserService;
import com.f313.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class RegisterAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
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
	public String register(){
		String pid = request.getParameter("Name");
		String psw = request.getParameter("Mima");
		
		Flag flag = new Flag();
		flag.setSign("one");
		flag.setUrl("/tk/login.jsp");
		
		
		try {
			if(userService.register(pid, psw)){
				
				res = JSONObject.fromObject(flag).toString();
				return "suc";
				//request.getSession().setAttribute("user", pid);
				//request.getRequestDispatcher("/user.html").forward(request, response);
				
				//String abc=JSONArray.fromObject(list).toString();
				//System.out.println(abc+"111111111212");
				//response.getOutputStream().write(abc.getBytes());
			}
			else{
				//request.setAttribute("tip", "输入有误，请重新注册");
				//request.getRequestDispatcher("/register.jsp").forward(request, response);
				//flag.setSign("two");
				//flag.setUrl("/tk/register.jsp");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			}
		} catch (Exception e) {
			return "suc";
		}
	}
	}

