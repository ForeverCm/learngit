package com.f313.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.entity.User;
import com.f313.service.ManagerService;
import com.f313.service.UserService;
import com.f313.service.impl.ManagerServiceImpl;
import com.f313.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;


	

public class LoginServlet extends HttpServlet {

	private static final UserService userService;
	private static final ManagerService managerService;
	
	static{
		userService = new UserServiceImpl();
	}
	static{
		managerService = new ManagerServiceImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String psw = request.getParameter("psw");
		String iden = request.getParameter("iden");
		
		if( iden.equals("user")){
			try {
				if(userService.login(pid, psw)){
					request.getSession().setAttribute("user", pid);
					request.getRequestDispatcher("/user.html").forward(request, response);
					
					//String abc=JSONArray.fromObject(list).toString();
					//System.out.println(abc+"111111111212");
					//response.getOutputStream().write(abc.getBytes());
				}
				else{
					request.setAttribute("tip", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				response.sendRedirect("mistake.jsp");
			}
			
		}
		else{
			try {
				if(managerService.login(pid, psw)){
					request.getSession().setAttribute("manager", pid);
					request.getRequestDispatcher("/manager.html").forward(request, response);
					
					//String abc=JSONArray.fromObject(list).toString();
					//System.out.println(abc+"111111111212");
					//response.getOutputStream().write(abc.getBytes());
				}
				else{
					request.setAttribute("tip", "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				response.sendRedirect("mistake.jsp");
			}
		}
	}

}

