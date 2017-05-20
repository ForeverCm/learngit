package com.f313.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.mark.Flag;
import com.f313.service.UserService;
import com.f313.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;

public class RegisterServlet extends HttpServlet  {
	
	private static final UserService userService;
	static{
		userService = new UserServiceImpl();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pid = request.getParameter("Name");
		String psw = request.getParameter("Mima");
		
		Flag flag = new Flag();
		flag.setSign("one");
		flag.setUrl("/tk/login.jsp");
		
		
		try {
			if(userService.register(pid, psw)){
				
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
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
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			}
		} catch (Exception e) {
			response.sendRedirect("mistake.jsp");
		}
	}

}
